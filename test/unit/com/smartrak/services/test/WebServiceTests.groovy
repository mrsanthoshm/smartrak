/**
 * 
 */
package com.smartrak.services.test

import org.apache.commons.logging.LogFactory
import org.junit.Ignore
import org.junit.Test

import wslite.soap.SOAPResponse

import com.smartrak.domain.UserDetails
import com.smartrak.enumeration.UserLevelEnum
import com.smartrak.services.WebServiceClientFactory

/**
 * @author santhosh
 *
 */

class WebServiceTests {
	
	def log = LogFactory.getLog(getClass())
	
	@Test
	void testDeleteUser() {
		UserDetails userDetails = new UserDetails()
		userDetails.setAuthentication("DD94BADBE8EF4DCBA1020AC163669A2E")
		userDetails.setUserId(13299)
		SOAPResponse response = WebServiceClientFactory.deleteUser(userDetails)

		log.debug '*** It works! ***'  
		
		assert 200 == response.httpResponse.statusCode
		assert "OK" == response.httpResponse.statusMessage
	}
	
	@Ignore
	void testCreateUser() {
		UserDetails userDetails = new UserDetails()
		userDetails.setAuthentication("DD94BADBE8EF4DCBA1020AC163669A2E")
		userDetails.setCompanyId(924)
		userDetails.setUserName('san')
		userDetails.setRealName('san mani')
		userDetails.setPhoneNumber('')
		userDetails.setEmailAddress('san@waiakto.ac.nz')
		userDetails.setUserLevel(UserLevelEnum.BOOKING_ONLY_USER.toString())
		userDetails.setHasFleetManagement(Boolean.FALSE.booleanValue());
		userDetails.setSharesMapTags(Boolean.FALSE.booleanValue());
		userDetails.setSendPasswordEmail(Boolean.FALSE.booleanValue());
		
		def userId = WebServiceClientFactory.createUser(userDetails).toString()
		
		log.debug '~~~~~~~~~~~~~~'+userId
	}
	
	@Ignore
	void testReteriveUser(){
		UserDetails userDetails = new UserDetails()
		userDetails.setAuthentication("DD94BADBE8EF4DCBA1020AC163669A2E")
		userDetails.setCompanyId(924)
	
		WebServiceClientFactory.reterieveUsers(userDetails).each { 
			log.debug 'userId: '+ it.UserId
			log.debug 'Name:   '+ it.Name
			log.debug 'UserName:   '+ it.UserName
			log.debug 'PhoneNumber:   '+ it.PhoneNumber
			log.debug 'EmailAddress:   '+ it.EmailAddress
			log.debug 'UserLevel:   '+ it.UserLevel
		}
	}
	
	@Test
	void testGenerateAuthenticateURL(){
		UserDetails userDetails = new UserDetails()
		userDetails.setAuthentication("DD94BADBE8EF4DCBA1020AC163669A2E")
		userDetails.setUserId(4482)
		def url = WebServiceClientFactory.generateAuthenticatedURL(userDetails)
		
		log.debug "***********  "+url
		assert "" != url
	}
	
	@Test
	void testUpdateUsersEmail(){
		UserDetails userDetails = new UserDetails()
		userDetails.setAuthentication("7832C28001364A49986E23A5EB88C614")
		userDetails.setUserId(13320)
		userDetails.setEmailAddress("santhosh@waikato.ac.nz")
		SOAPResponse response = WebServiceClientFactory.updateUserEmail(userDetails)
		
		assert 200 == response.httpResponse.statusCode
		assert "OK" == response.httpResponse.statusMessage
	}

}
