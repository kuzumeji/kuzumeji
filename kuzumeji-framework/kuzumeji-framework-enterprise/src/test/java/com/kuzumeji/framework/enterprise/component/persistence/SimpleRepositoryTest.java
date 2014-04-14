// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
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
        return ArchiveFactory.createJarWithCdi()
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsResource("config.properties").addAsResource("error-messages.properties");
    }
    @Test
    public final void test() throws PersistenceException {
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
        try {
            testee.save(new PersistableTestee("code#01", "name#01"));
        } catch (final PersistenceException e) {
            fail(e.getLocalizedMessage());
        }
        try {
            testee.save(new PersistableTestee("code#01", "name#01"));
            fail();
        } catch (final PersistenceException e) {
            assertThat(e.getApplicationMessage(),
                is("一意キー制約の違反です。テストエンティティ[コード=code#01]\t一意キー制約の違反です。テストエンティティ[なまえ=name#01]"));
        }
    }
}
