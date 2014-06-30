package net.java.pathfinder.internal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
/**
 * グラフDAO
 * @author nilcy
 */
@ApplicationScoped
@SuppressWarnings("static-method")
public class GraphDao implements Serializable {
    /** 識別番号 */
    private static final long serialVersionUID = 2960467968936281469L;
    /** 乱数生成器 */
    private final Random random = new Random();
    /**
     * ロケーション一覧を取得できます。
     * @return ロケーション一覧
     */
    public List<String> listLocations() {
        return new ArrayList<>(Arrays.asList("CNHKG", "AUMEL", "SESTO", "FIHEL", "USCHI", "JNTKO",
            "DEHAM", "CNSHA", "NLRTM", "SEGOT", "CNHGH", "USNYC", "USDAL"));
    }
    /**
     * 航海番号の取得
     * @param from 出発地
     * @param to 到着地
     * @return 航海番号
     */
    public String getVoyageNumber(final String from, final String to) {
        final int i = random.nextInt(5);
        switch (i) {
            case 0:
                return "0100S";
            case 1:
                return "0200T";
            case 2:
                return "0300A";
            case 3:
                return "0301S";
            default:
                return "0400S";
        }
    }
}
