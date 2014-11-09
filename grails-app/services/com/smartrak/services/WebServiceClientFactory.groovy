/**
 *
 */
package com.smartrak.services

import wslite.soap.SOAPClient
import wslite.soap.SOAPResponse

import com.smartrak.domain.UserDetails

/**
 * @author santhosh
 */
class WebServiceClientFactory {
	
	private static String GET_USER_WS_URL         = "https://test2.smartrak.co.nz/ClientAdmin/UserManagement.asmx"
	
	private static String UPDATE_USER_WS_URL      = "https://test2.smartrak.co.nz/ClientAdmin/PoolBookingManagement.asmx"
	
	/**
	 * Create and retrieve the user detail based upon request parameter
	 * @return SOAPClient - connect to soap client
	 */
	private static SOAPClient getUser(){
		def soapClient = new SOAPClient(GET_USER_WS_URL)
		return soapClient;
	}
	
	/**
	 * Update user details based upon request parameter
	 * @return SOAPClient - connect to soap client
	 */
	private static SOAPClient updateUser(){
		def soapClient = new SOAPClient(UPDATE_USER_WS_URL)
		return soapClient;
	}
	
	/**
	 * Creates a new user for use with the smartrak system
	 * @param userDetails - POJO class
	 * @return int - internal id of the newly created user
	 */
	public static Object createUser(UserDetails userDetails){
		def soapClient = getUser()
	    
		def response = soapClient.send {
		   body {
			   CreateUser(xmlns:"http://smartrak.co.nz/") {
				   authentication(userDetails.getAuthentication())
				   companyId(userDetails.getCompanyId())
				   userName(userDetails.getUserName())
				   realName(userDetails.getRealName())
				   phoneNumber(userDetails.getPhoneNumber())
				   emailAddress(userDetails.getEmailAddress())
				   userLevel(userDetails.getUserLevel())
				   hasFleetManagement(userDetails.isHasFleetManagement())
				   sharesMapTags(userDetails.isSharesMapTags())
				   sendPasswordEmail(userDetails.isSendPasswordEmail())
			   }
		   }
		}
		return response.CreateUserResponse.CreateUserResult
	}
	
	/**
	 * Retrieves all users currently active in the smartrak system
	 * @param userDetails - POJO class
	 * @return list of user details
	 */
	public static Object reterieveUsers(UserDetails userDetails){
		def soapClient = getUser()
		def response = soapClient.send {
		   body {
			   GetUsers(xmlns:"http://smartrak.co.nz/") {
				   authentication(userDetails.getAuthentication())
				   companyId(userDetails.getCompanyId())
			   }
		   }
		}
		return response.GetUsersResponse.GetUsersResult.UserDef
	}
	
	/**
	 * Deletes an active user in the Smartrak system
	 * @param userDetails - POJO class
	 * @return SOAPResponse
	 */
	public static SOAPResponse deleteUser(UserDetails userDetails){
		def soapClient = getUser()
		def response = soapClient.send {
			body {
				DeleteUser(xmlns:"http://smartrak.co.nz/") {
					authentication(userDetails.getAuthentication())
					userId(userDetails.getUserId())
				}
			}
		 }
		return response
	}
	
	/**
	 * Update the cost center assigned to an active user in the smartrak pool booking system
	 * @return SOAPResponse
	 */
	public static SOAPResponse updateUserCostCentre(){
		def soapClient = updateUser()
		def response = soapClient.send {
			body {
				UpdatePoolBookingUserCostCentre(xmlns:"http://smartrak.co.nz/") {
					authentication()
					userId()
					costCentreDescription()
				}
			}
		 }
		 return response
	}
	
	/**
	 * Updates the email address of an active user in the pool booking system
	 * @return SOAPResponse
	 */
	public static SOAPResponse updateUserEmail(UserDetails userDetails){
		def soapClient = updateUser()
		def response = soapClient.send {
			body {
				UpdatePoolBookingUserEmail(xmlns:"http://smartrak.co.nz/") {
					authentication(userDetails.getAuthentication())
					userId(userDetails.getUserId())
					email(userDetails.getEmailAddress())
				}
			}
		 }
		 return response
	}
	
	/**
	 * Updates the Job title of an active user in the smartrak pool booking system
	 * @return SOAPResponse
	 */
	public static SOAPResponse updateUserJobTitle(){
		def soapClient = updateUser()
		def response = soapClient.send {
			body {
				UpdatePoolBookingUserJobTitle(xmlns:"http://smartrak.co.nz/") {
					authentication()
					userId()
					jobTitle()
				}
			}
		 }
		 return response
	}
	
	/**
	 * Updates the manager assigned to an active user in the smartrak pool booking system
	 * @return SOAPResponse
	 */
	public static SOAPResponse updateUserManager(){
		def soapClient = updateUser()
		def response = soapClient.send {
			body {
				UpdatePoolBookingUserManager(xmlns:"http://smartrak.co.nz/") {
					authentication()
					userId()
					manager()
				}
			}
		}
		return response
	}
	
	/**
	 * Updates the real name of an active user in the smartrak pool booking system
	 * @return SOAPResponse
	 */
	public static SOAPResponse updateUserName(){
		def soapClient = updateUser()
		def response = soapClient.send {
			body {
				UpdatePoolBookingUserName(xmlns:"http://smartrak.co.nz/") {
					authentication()
					userId()
					firstName()
					lastName()
				}
			}
		}
		return response
	}
	
	/**
	 * Updates the phone number of an active user in the smartrak pool booking system
	 * @return SOAPResponse
	 */
	public static SOAPResponse updateUserPhoneNumber(){
		def soapClient = updateUser()
		def response = soapClient.send {
			body {
				UpdatePoolBookingUserPhoneNumber(xmlns:"http://smartrak.co.nz/") {
					authentication()
					userId()
					phoneNumber()
				}
			}
		}
		return response
	}
	
	/**
	 * Generates a limited use, pre-authenticated URL which automatically logs the user for which 
	 * it was generated in to the smartrak system
	 * 
	 * @return String - Pre-authenticated URL to be used for accessing the smartrak system
	 */
	public static String generateAuthenticatedURL(UserDetails userDetails){
		def soapClient = getUser()
		def response = soapClient.send {
			body {
				GetSudoUrl(xmlns:"http://smartrak.co.nz/") {
					authentication(userDetails.getAuthentication())
					userId(userDetails.getUserId())
				}
			}
		}
		return response.GetSudoUrlResponse.GetSudoUrlResult
	}
}
