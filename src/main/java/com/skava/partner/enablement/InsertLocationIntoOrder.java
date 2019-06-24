/*******************************************************************************
 * Copyright ©2002-2018 Skava - All rights reserved.
 * All information contained herein is, and remains the property of Skava.
 * Skava including, without limitation, all software and other elements thereof,
 * are owned or controlled exclusively by Skava and protected by copyright, patent
 * and other laws. Use without permission is prohibited.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * 
 * For further information contact Skava at info@skava.com.
 * 
 * * 
 * This code is provided solely for use in articulating the Skava Commerce API 
 * and system and is not warrented to be fit for any purpose 
 * 
 ******************************************************************************/
package com.skava.partner.enablement;

import java.util.HashMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.converter.stream.InputStreamCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.skava.oms.model.Order;
import com.skava.oms.orchestration.model.OrderOms;
import com.skava.oms.orchestration.transformation.OMSTransformation;
import com.skava.partner.enablement.model.OrderOmsExtended;

/**
 * ConvertAndInsert.
 */
@Service(value = "InsertLocationIntoOrder")
public class InsertLocationIntoOrder implements Processor {

  /** LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(InsertLocationIntoOrder.class);

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
   */
  
  /**
   * This processor take a current OrderOMS object, copy constructs an OrderOMSExtended object from it and adds a JSON encoded 
   * Location to the extended OMS properties. 
   * 
   *  This processor assumes that the body of the Exchange in is a valid OrderOMS object with its fields properly set. This processor looks for 
   *  a JSON string property in the "locationResponse" property of the exchange and copies it into an extendedPropertie hashmap, under the key 
   *  "DeliveryLocation"
   *  
   *  This processor does not validate either the orderOMS object or the string provided in the locationResponse property of the esxchange. 
   */
  @Override
  public void process(Exchange exchange) {
    LOGGER.info("InsertLocationIntoOrder Processor Log");
    OrderOms orderOms =  (OrderOms) exchange.getIn().getBody(); 
    OrderOmsExtended extendedOms = new OrderOmsExtended();
    extendedOms.setCustomer(orderOms.getCustomer());
    extendedOms.setOrderInfo(orderOms.getOrderInfo());
    extendedOms.setPayments(orderOms.getPayments());
    extendedOms.setReturns(orderOms.getReturns());
    extendedOms.setShipmentPackages(orderOms.getShipmentPackages());
    String location = "no location available";
    try
    {
    	location = (String)exchange.getProperty("locationResponse");
    }
    catch(Exception e)
    {
    LOGGER.error("InsertLocationIntoOrder: Error getting location from exchange");	
    }
    HashMap<String,String> extendedProps = new HashMap<String,String>();
    extendedProps.putIfAbsent("DeliveryLocation", location);
    extendedOms.setExtensionProperties(extendedProps);
    exchange.getIn().setBody(extendedOms);
    LOGGER.info("InsertLocationIntoOrder Processor Log Terminated");
  }

}