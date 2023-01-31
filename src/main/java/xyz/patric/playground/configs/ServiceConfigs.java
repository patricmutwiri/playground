/*
 * Copyright (c) 2023.
 * @author Patrick Mutwiri on 1/31/23, 6:01 PM
 */

package xyz.patric.playground.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ServiceConfigs {
    @Autowired
    Environment environment;
}
