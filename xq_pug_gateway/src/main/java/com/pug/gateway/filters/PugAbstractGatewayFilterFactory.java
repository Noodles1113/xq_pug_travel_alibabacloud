package com.pug.gateway.filters;

import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import java.util.Arrays;
import java.util.List;

public abstract class PugAbstractGatewayFilterFactory extends AbstractGatewayFilterFactory<PugStripPrefixGatewayFilterFactory.PugConfig> {
    private static final String[] KEY_ARRAY = {"parts"};

    public PugAbstractGatewayFilterFactory() {
        super(PugAbstractGatewayFilterFactory.PugConfig.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY_ARRAY);
    }

    public static class PugConfig {
        int parts;

        public int getParts() {
            return parts;
        }

        public void setParts(int parts) {
            this.parts = parts;
        }
    }

}
