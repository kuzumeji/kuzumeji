// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
/**
 * @see UniqueConstraintsListener
 * @author nilcy
 */
public class UniqueConstraintsListenerProducer {
    /** コンストラクタ */
    public UniqueConstraintsListenerProducer() {
    }
    // @Produces
    // @PersistableTesteeUKcode
    // public UniqueConstraintsListener<PersistableTestee> createPersistableTesteeUKcode() {
    // return new DefaultUniqueConstraintsListener<PersistableTestee>(
    // "PersistableTestee.findUK_code", "PersistableTestee.UK_code", "code");
    // }
    // @Produces
    // @PersistableTesteeUKname
    // public UniqueConstraintsListener<PersistableTestee> createPersistableTesteeUKname() {
    // return new DefaultUniqueConstraintsListener<PersistableTestee>(
    // "PersistableTestee.findUK_name", "PersistableTestee.UK_name", "name");
    // }
}
