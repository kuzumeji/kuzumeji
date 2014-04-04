// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.standard.component;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle.Control;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * コンフィグヘルパー
 * @author nilcy
 */
public final class PropertiesHelper {
    /** ロガー */
    private static final Logger LOG = LoggerFactory.getLogger(PropertiesHelper.class);
    /** コンフィグ */
    private PropertiesConfiguration config;
    /**
     * コンストラクタ
     * @param baseName プロパティ定義ベース名
     */
    public PropertiesHelper(final String baseName) {
        try {
            config = new PropertiesConfiguration(createFileName(baseName));
        } catch (final ConfigurationException e) {
            LOG.warn(e.getLocalizedMessage(), e);
            config = null;
        }
    }
    /**
     * テキスト値の取得
     * @param key キー
     * @return テキスト値
     */
    public String getText(final String key) {
        assert config != null;
        return config.getString(key);
    }
    /**
     * テキスト値の取得
     * @param key キー
     * @param arguments 可変長引数オブジェクト
     * @return テキスト値
     */
    public String getText(final String key, final Object... arguments) {
        assert config != null;
        final String text = config.getString(key);
        assert text != null;
        return MessageFormat.format(text, arguments);
    }
    /**
     * テキスト値の取得
     * @param key キー
     * @return テキスト値
     */
    public String[] getTexts(final String key) {
        assert config != null;
        return config.getStringArray(key);
    }
    /**
     * 数値の取得
     * @param key キー
     * @return 数値
     */
    public int getNumeric(final String key) {
        assert config != null;
        return config.getInt(key);
    }
    /**
     * プロパティ値の設定
     * @param key キー
     * @param value プロパティ値
     */
    public void setProperty(final String key, final Object value) {
        assert config != null;
        config.setProperty(key, value);
    }
    /**
     * プロパティの保存
     * @throws ConfigurationException コンフィグ例外
     */
    public void save() throws ConfigurationException {
        assert config != null;
        config.save();
    }
    /**
     * ファイル名の作成
     * @param baseName ベース名
     * @return ファイル名
     */
    private static String createFileName(final String baseName) {
        final String languageTag = String.format("%s-%s", SystemUtils.USER_LANGUAGE,
            SystemUtils.USER_COUNTRY);
        final Control control = Control.getControl(Control.FORMAT_DEFAULT);
        final Collection<Locale> locales = control.getCandidateLocales("messages",
            Locale.forLanguageTag(languageTag));
        for (final Locale locale : locales) {
            try {
                final String bundleName = control.toBundleName(baseName, locale);
                final String resourceName = control.toResourceName(bundleName, "properties");
                new PropertiesConfiguration(resourceName);
                return resourceName;
            } catch (final ConfigurationException e) {
            }
        }
        throw new StandardRuntimeException(String.format("PROPERTY is NOT_FOUND. [baseName=%s]",
            baseName));
    }
}
