/**
 * 
 */
package com.smartrak.controllers

import org.apache.cxf.endpoint.Client
import org.apache.cxf.frontend.ClientProxy
import org.apache.cxf.transport.http.HTTPConduit
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy


/**
 * @author santhosh
 *
 */
class PoolBookingController {
	
	def index = {
		def result
		if (params?.value && params.from) {
			
		}
		flash.message = result
	}

}
