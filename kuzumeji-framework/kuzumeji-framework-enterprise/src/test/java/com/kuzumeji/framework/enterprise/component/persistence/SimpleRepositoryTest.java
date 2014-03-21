// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kuzumeji.framework.testing.ArchiveFactory;
/**
 * @see Repository
 * @see SimpleRepository
 * @author nilcy
 */
@RunWith(Arquillian.class)
@Transactional(value = TransactionMode.ROLLBACK)
@SuppressWarnings("all")
public class SimpleRepositoryTest {
    @Inject
    private SimpleRepository<PersistableTestee> testee;
    @Inject
    private Logger log;
    @Deployment
    public static JavaArchive deploy() {
        return ArchiveFactory.createJar_Jpa();
    }
    @Test
    public final void test() {
        assertThat(testee, is(not(nullValue())));
        // 追加してID発番,永続管理ができることを確認する。
        PersistableTestee entity = new PersistableTestee("code#01", "name#01");
        entity = testee.save(entity);
        assertThat(entity, is(not(nullValue())));
        assertThat(entity.getId(), is(not(nullValue())));
        assertThat(entity.isPersisted(), is(true));
        // 検索して追加内容と比較する。
        entity = testee.find(entity.identity());
        assertThat(entity, is(not(nullValue())));
        assertThat(entity.getCode(), is("code#01"));
        assertThat(entity.getName(), is("name#01"));
        // 変更する。
        entity.setName("name#02");
        entity = testee.save(entity);
        // 検索して追加内容と比較する。
        entity = testee.find(entity.identity());
        assertThat(entity, is(not(nullValue())));
        assertThat(entity.getName(), is("name#02"));
        // 削除して該当データなしを確認する。
        testee.delete(entity);
        entity = testee.find(entity.identity());
        assertThat(entity, is(nullValue()));
    }
    @Test
    public final void testUK() {
        assertThat(testee, is(not(nullValue())));
        testee.save(new PersistableTestee("code#01", "name#01"));
        final Map<String, Object> filter = new HashMap<>();
        filter.put("code", "code#01");
        assertThat(testee.findOne("PersistableTestee.findUK", filter).getCode(), is("code#01"));
        assertThat(testee.findOne("findUK", filter).getCode(), is("code#01"));
    }
    @Test
    public final void testName() {
        final Logger log = LoggerFactory.getLogger(this.getClass());
        log.info(this.getClass().getCanonicalName());
        log.info(this.getClass().getSimpleName());
        log.info("match : {}", "PersistableTestee.findUK".matches("PersistableTestee.*"));
    }
}
