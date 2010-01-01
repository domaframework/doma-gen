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
    public static _Name get() {
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
