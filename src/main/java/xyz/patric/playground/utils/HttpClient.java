/*
 * Copyright (c) 2023.
 * @author Patrick Mutwiri on 1/31/23, 6:01 PM
 */

package xyz.patric.playground.utils;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HttpClient {}
