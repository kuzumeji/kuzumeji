// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.service;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import org.slf4j.Logger;
/**
 * 手動タイマーサービス
 * @author nilcy
 */
@Singleton
public class ProgramaticTimerService {
    /** タイマーサービス */
    @Resource
    private TimerService timerService;
    /** 最終実行日時 */
    private Date lastTimeout;
    /** ロガー */
    @Inject
    private Logger log;
    /** コンストラクタ */
    public ProgramaticTimerService() {
    }
    /**
     * タイマー作成
     * @param duration タイムアウト時間(ミリ秒)
     */
    public void setTimer(final long duration) {
        log.info("タイムアウト時間(ミリ秒) : {}", duration);
        timerService.createTimer(duration, "手動タイマー#01");
    }
    /**
     * タイムアウト処理
     * @param timer タイマー
     */
    @Timeout
    public void timeout(final Timer timer) {
        setLastTimeout(new Date());
        log.info("手動タイマーサービスが終了しました。[{}]", timer.getInfo());
    }
    /**
     * {@link #lastTimeout} の取得
     * @return {@link #lastTimeout}
     */
    public String getLastTimeout() {
        return lastTimeout != null ? lastTimeout.toString() : "N/A";
    }
    /**
     * {@link #lastTimeout} の設定
     * @param lastTimeout {@link #lastTimeout}
     */
    public void setLastTimeout(final Date lastTimeout) {
        this.lastTimeout = lastTimeout;
    }
}
