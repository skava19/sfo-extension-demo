/**
 * *******************************************************************************
 * Copyright ©2002-2019 Skava - All rights reserved.
 *  
 * All information contained herein is, and remains the property of Skava.
 * Skava including, without limitation, all software and other elements thereof, 
 * are owned or controlled exclusively by Skava and protected by copyright, patent
 * and other laws. Use without permission is prohibited. 
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *   
 * For further information contact Skava at info@skava.com.
 * 
 * This code is provided solely for use in articulating the Skava Commerce API 
 * and system and is not warrented to be fit for any purpose 
 * 
 * *******************************************************************************
 */
package com.skava.partner.config1;

import com.skava.orchestration.CamelContextUtilityBean;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Custom camel configuration
 */
@Configuration
public class CustomCamelConfiguration implements CamelContextConfiguration {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomCamelConfiguration.class);

  static {
    LOGGER.info("###### Custom camel configuration found ######");
  }

  @Autowired
  private CamelContextUtilityBean camelContextUtilityBean;

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.camel.spring.boot.CamelContextConfiguration#beforeApplicationStart(org.apache.camel.CamelContext)
   */
  public void beforeApplicationStart(CamelContext camelContext) {
    LOGGER.info("##### Custom camel configuration started #####");
    try {
      // Load external routes and rests
      camelContextUtilityBean.loadXmlRoutes(camelContext, "classpath:routes/**/*.xml");
    }
    catch (Exception e)
    {
     LOGGER.error("Exception occured while load base plugin routes.");
     LOGGER.error(e.getMessage());
     throw new RuntimeException("Exception occurred while loading base custom routes", e);
    }
    try { 
      camelContextUtilityBean.loadXmlRests(camelContext, "classpath:rests/*.xml");
    }
    
    catch (Exception e)
    {
     LOGGER.error("Exception occured while load Rest  plugin routes.");
     LOGGER.error(e.getMessage());
     throw new RuntimeException("Exception occurred while loading base custom routes", e);
    }
    
    /* Route interception here */
    
    /* camelContext.getRouteDefinition("constructSFItem")
      .adviceWith(camelContext, new AdviceWithRouteBuilder() {
        @Override
        public void configure() {
          interceptSendToEndpoint("direct:getOrderId")
            .skipSendToOriginalEndpoint()
            .log("Redirect to get order with ETA ")
            .to("direct:getOrderIdEta");
        }
      }); */
      
      
    
    
    LOGGER.info("##### Custom camel configuration routes loaded #####");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.camel.spring.boot.CamelContextConfiguration#afterApplicationStart(org.apache.camel.CamelContext)
   */
  public void afterApplicationStart(CamelContext camelContext) {
    LOGGER.info("##### Custom camel configuration done #####");
  }

}
