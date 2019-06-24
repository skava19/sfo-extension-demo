/**********************************************************************************
 * Copyright ©2002-2018 Skava - All rights reserved.
 * 
 * All information contained herein is, and remains the property of Skava.
 * Skava including, without limitation, all software and other elements thereof,
 * are owned or controlled exclusively by Skava and protected by copyright, patent
 * and other laws. Use without permission is prohibited.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *  
 * For further information contact Skava at info@skava.com.
 * 
 * 
 * This code is provided solely for use in articulating the Skava Commerce API 
 * and system and is not warrented to be fit for any purpose 
 * 
 *********************************************************************************/

package com.skava.partner.enablement.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skava.oms.orchestration.model.OrderInfo;
import com.skava.oms.orchestration.model.ReturnOrderOms;
import com.skava.oms.orchestration.model.ShipmentPackage;
import com.skava.orchestration.model.common.CustomerOrchestration;
import com.skava.orchestration.model.common.PaymentOrchestration;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Order object which contains the order information
 */
@ApiModel(description = "This object contains the order information.")
@NoArgsConstructor
@Getter
@Setter
public class OrderOmsExtended {

  /**
   * Order level details 
  **/
  @ApiModelProperty(value = "Indicates the unique ID given to the order")
  @JsonProperty("orderInfo")
  private OrderInfo orderInfo;

  /**
   * customer details 
  **/
  @ApiModelProperty(value = "Indicates customer details of the order")
  @JsonProperty("customer")
  private CustomerOrchestration customer;

  /**
   * Indicates shipment packages for the order
  **/
  @ApiModelProperty(value = "Indicates the shipment packages of the order.")
  @JsonProperty("shipmentPackages")
  private List<ShipmentPackage> shipmentPackages = new ArrayList<>();

  /**
   * Indicates the payments methods for the order
  **/
  @ApiModelProperty(value = "Indicates the payment methods of the order.")
  @JsonProperty("payments")
  private List<PaymentOrchestration> payments = new ArrayList<>();

  /**
   * Indicates returned orders if any
  **/
  @ApiModelProperty(value = "This model contains the return order details and the items information.")
  @JsonProperty("returns")
  private List<ReturnOrderOms> returns = new ArrayList<>();
  
  /**
   * 
   */
  
   @ApiModelProperty(value = "This model contains the extension Properties Hashmap.")
   @JsonProperty("extensionProperties")
   private HashMap<String,String> extensionProperties = new HashMap<String,String>();
}
