package net.java.pathfinder.api;
import java.io.Serializable;
import java.util.Date;
/**
 * 貨物のルートを記述し、グラフを通るパスのエッジを表します。
 * <p/>
 * Represents an edge in a path through a graph, describing the route of a cargo.
 */
public class TransitEdge implements Serializable {
    /** 識別番号 */
    private static final long serialVersionUID = 7906061723775587185L;
    /** 航海番号 */
    private String voyageNumber;
    /** 出発地の国際ロケーション番号 */
    private String fromUnLocode;
    /** 到着地の国際ロケーション番号 */
    private String toUnLocode;
    /** 出発日時 */
    private Date fromDate;
    /** 到着日時 */
    private Date toDate;
    /** コンストラクタ */
    public TransitEdge() {
        // することが何もない (Nothing to do.)
    }
    /**
     * コンストラクタ
     * @param voyageNumber {@link #voyageNumber}
     * @param fromUnLocode {@link #fromUnLocode}
     * @param toUnLocode {@link #toUnLocode}
     * @param fromDate {@link #fromDate}
     * @param toDate {@link #toDate}
     */
    public TransitEdge(final String voyageNumber, final String fromUnLocode,
        final String toUnLocode, final Date fromDate, final Date toDate) {
        this.voyageNumber = voyageNumber;
        this.fromUnLocode = fromUnLocode;
        this.toUnLocode = toUnLocode;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    /**
     * {@link #voyageNumber} の取得
     * @return {@link #voyageNumber}
     */
    public String getVoyageNumber() {
        return voyageNumber;
    }
    /**
     * {@link #voyageNumber} の設定
     * @param voyageNumber {@link #voyageNumber}
     */
    public void setVoyageNumber(final String voyageNumber) {
        this.voyageNumber = voyageNumber;
    }
    /**
     * {@link #fromUnLocode} の取得
     * @return {@link #fromUnLocode}
     */
    public String getFromUnLocode() {
        return fromUnLocode;
    }
    /**
     * {@link #fromUnLocode} の設定
     * @param fromUnLocode {@link #fromUnLocode}
     */
    public void setFromUnLocode(final String fromUnLocode) {
        this.fromUnLocode = fromUnLocode;
    }
    /**
     * {@link #toUnLocode} の取得
     * @return {@link #toUnLocode}
     */
    public String getToUnLocode() {
        return toUnLocode;
    }
    /**
     * {@link #toUnLocode} の設定
     * @param toUnLocode {@link #toUnLocode}
     */
    public void setToUnLocode(final String toUnLocode) {
        this.toUnLocode = toUnLocode;
    }
    /**
     * {@link #fromDate} の取得
     * @return {@link #fromDate}
     */
    public Date getFromDate() {
        return fromDate;
    }
    /**
     * {@link #fromDate} の設定
     * @param fromDate {@link #fromDate}
     */
    public void setFromDate(final Date fromDate) {
        this.fromDate = fromDate;
    }
    /**
     * {@link #toDate} の取得
     * @return {@link #toDate}
     */
    public Date getToDate() {
        return toDate;
    }
    /**
     * {@link #toDate} の設定
     * @param toDate {@link #toDate}
     */
    public void setToDate(final Date toDate) {
        this.toDate = toDate;
    }
    // public String getFromUnLocode() {
    // return fromUnLocode;
    // }
    // public void setFromUnLocode(final String fromUnLocode) {
    // this.fromUnLocode = fromUnLocode;
    // }
    // public String getToUnLocode() {
    // return toUnLocode;
    // }
    // public void setToUnLocode(final String toUnLocode) {
    // this.toUnLocode = toUnLocode;
    // }
    // public Date getFromDate() {
    // return fromDate;
    // }
    // public void setFromDate(final Date fromDate) {
    // this.fromDate = fromDate;
    // }
    // public Date getToDate() {
    // return toDate;
    // }
    // public void setToDate(final Date toDate) {
    // this.toDate = toDate;
    // }
    @Override
    public String toString() {
        return "TransitEdge{" + "voyageNumber=" + voyageNumber + ", fromUnLocode=" + fromUnLocode
            + ", toUnLocode=" + toUnLocode + ", fromDate=" + fromDate + ", toDate=" + toDate + '}';
    }
}
