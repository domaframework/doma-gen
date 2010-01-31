/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.extension.gen.it;

/** */
@javax.annotation.Generated(value = { "Doma", "0.9.11-SNAPSHOT" }, date = "2010-01-02 02:26:03")
public final class _Name
        implements
        org.seasar.doma.internal.domain.DomainType<java.lang.String, org.seasar.doma.extension.gen.it.Name> {

    private static final _Name singleton = new _Name();

    private _Name() {
    }

    @Override
    public org.seasar.doma.extension.gen.it.Name newDomain(
            java.lang.String value) {
        return new org.seasar.doma.extension.gen.it.Name(value);
    }

    @Override
    public Class<org.seasar.doma.extension.gen.it.Name> getDomainClass() {
        return org.seasar.doma.extension.gen.it.Name.class;
    }

    @Override
    public org.seasar.doma.internal.domain.DomainWrapper<java.lang.String, org.seasar.doma.extension.gen.it.Name> getWrapper(
            org.seasar.doma.extension.gen.it.Name domain) {
        return new Wrapper(domain);
    }

    /**
     * @return the singleton
     */
    public static _Name getSingletonInternal() {
        return singleton;
    }

    private static class Wrapper extends org.seasar.doma.wrapper.StringWrapper
            implements
            org.seasar.doma.internal.domain.DomainWrapper<java.lang.String, org.seasar.doma.extension.gen.it.Name> {

        private org.seasar.doma.extension.gen.it.Name domain;

        private Wrapper(org.seasar.doma.extension.gen.it.Name domain) {
            if (domain == null) {
                this.domain = new org.seasar.doma.extension.gen.it.Name(
                        (java.lang.String) null);
            } else {
                this.domain = domain;
            }
        }

        @Override
        protected java.lang.String doGet() {
            return domain.getValue();
        }

        @Override
        protected void doSet(java.lang.String value) {
            domain = new org.seasar.doma.extension.gen.it.Name(value);
        }

        @Override
        public org.seasar.doma.extension.gen.it.Name getDomain() {
            return domain;
        }
    }
}
