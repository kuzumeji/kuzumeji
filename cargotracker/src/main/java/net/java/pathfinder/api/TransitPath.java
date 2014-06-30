package net.java.pathfinder.api;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 通行経路
 * @author nilcy
 */
@XmlRootElement
public class TransitPath implements Serializable {
    /** 識別番号 */
    private static final long serialVersionUID = 3561585936114963907L;
    /** 通行端線リスト */
    private List<TransitEdge> transitEdges;
    /** コンストラクタ */
    public TransitPath() {
        transitEdges = new ArrayList<>();
    }
    /**
     * コンストラクタ
     * @param transitEdges 通行端線リスト
     */
    public TransitPath(final List<TransitEdge> transitEdges) {
        this.transitEdges = transitEdges;
    }
    /**
     * 通行端線リストの取得
     * @return 通行端線リスト
     */
    public List<TransitEdge> getTransitEdges() {
        return transitEdges;
    }
    /**
     * 通行端線リストの設定
     * @param transitEdges 通行端線リスト
     */
    public void setTransitEdges(final List<TransitEdge> transitEdges) {
        this.transitEdges = transitEdges;
    }
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "TransitPath{" + "transitEdges=" + transitEdges + '}';
    }
}
