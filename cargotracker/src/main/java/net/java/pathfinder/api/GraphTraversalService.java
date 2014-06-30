package net.java.pathfinder.api;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import net.java.pathfinder.internal.GraphDao;
/**
 * 図表縦貫サービス? (グラフトラバーサルサービス)
 * @author nilcy
 */
@Stateless
@Path("/graph-traversal")
public class GraphTraversalService {
    /** グラフDAO */
    @Inject
    private GraphDao dao;
    /** 乱数生成器 */
    private final Random random = new Random();
    /** 1分(ミリ秒) */
    private static final long ONE_MIN_MS = 1000 * 60;
    /** 1日(ミリ秒) */
    private static final long ONE_DAY_MS = ONE_MIN_MS * 60 * 24;
    /**
     * 最短経路の検索
     * @param originUnLocode 出発地の国際ロケーションコード
     * @param destinationUnLocode 到着地の国際ロケーションコード
     * @param deadline 最終期限
     * @return 最短経路の通行経路リスト
     */
    @GET
    @Path("/shortest-path")
    @Produces({ "application/json", "application/xml; qs=.75" })
    // TODO 制約の多言語メッセージを追加すること。 (Add internationalized messages for constraints.)
    public List<TransitPath> findShortestPath(
        @NotNull @Size(min = 5, max = 5) @QueryParam("origin") final String originUnLocode,
        @NotNull @Size(min = 5, max = 5) @QueryParam("destination") final String destinationUnLocode,
        @QueryParam("deadline") final String deadline) {
        Date date = nextDate(new Date());
        List<String> allVertices = dao.listLocations();
        allVertices.remove(originUnLocode);
        allVertices.remove(destinationUnLocode);
        final int candidateCount = getRandomNumberOfCandidates();
        final List<TransitPath> candidates = new ArrayList<>(candidateCount);
        for (int i = 0; i < candidateCount; i++) {
            allVertices = getRandomChunkOfLocations(allVertices);
            final List<TransitEdge> transitEdges = new ArrayList<>(allVertices.size() - 1);
            final String firstLegTo = allVertices.get(0);
            Date fromDate = nextDate(date);
            Date toDate = nextDate(fromDate);
            date = nextDate(toDate);
            transitEdges.add(new TransitEdge(dao.getVoyageNumber(originUnLocode, firstLegTo),
                originUnLocode, firstLegTo, fromDate, toDate));
            for (int j = 0; j < (allVertices.size() - 1); j++) {
                final String current = allVertices.get(j);
                final String next = allVertices.get(j + 1);
                fromDate = nextDate(date);
                toDate = nextDate(fromDate);
                date = nextDate(toDate);
                transitEdges.add(new TransitEdge(dao.getVoyageNumber(current, next), current, next,
                    fromDate, toDate));
            }
            final String lastLegFrom = allVertices.get(allVertices.size() - 1);
            fromDate = nextDate(date);
            toDate = nextDate(fromDate);
            transitEdges.add(new TransitEdge(dao.getVoyageNumber(lastLegFrom, destinationUnLocode),
                lastLegFrom, destinationUnLocode, fromDate, toDate));
            candidates.add(new TransitPath(transitEdges));
        }
        return candidates;
    }
    /**
     * 翌日の取得
     * @param date 起点日
     * @return 翌日 (起点日 + 1日 ± 500分)
     */
    private Date nextDate(final Date date) {
        return new Date(date.getTime() + ONE_DAY_MS + ((random.nextInt(1000) - 500) * ONE_MIN_MS));
    }
    /**
     * 乱数候補の取得
     * @return 乱数候補 (3-6)
     */
    private int getRandomNumberOfCandidates() {
        return 3 + random.nextInt(3);
    }
    /**
     * ロケーションのランダムチャンクを取得
     * @param allLocations 全ロケーション
     * @return ロケーションのランダムチャンク(全ロケーションをシャッフルした0-6個分)
     */
    @SuppressWarnings("static-method")
    private List<String> getRandomChunkOfLocations(final List<String> allLocations) {
        Collections.shuffle(allLocations);
        final int total = allLocations.size();
        final int chunk = total > 4 ? 1 + new Random().nextInt(5) : total;
        return allLocations.subList(0, chunk);
    }
}
