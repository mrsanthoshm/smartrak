/**
 * 
 */
package com.smartrak.enumeration

/**
 * @author santhosh
 *
 */
enum UserLevelEnum {
	
	BOOKING_ONLY_USER("BookingOnlyUser"),MAP_USER("MapUser")
	
	final String value
	
	UserLevelEnum(String value) { this.value = value }
	
	String toString() { value }
	String getKey() { name() }

}
