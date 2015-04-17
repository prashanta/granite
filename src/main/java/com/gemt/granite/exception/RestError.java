package com.gemt.granite.exception;

public enum RestError {
		
	NO_DATABASE_CONNECTION(1, "No database connection"),
	
	PART_NONEXISTENT(101, "Part does not exist"),

	PARTS_NOT_FOUND(102, "Parts not found in search"),
	
	PART_REVISION_NOT_FOUND(103, "Part revision not found"),
	
	PART_REVISION_APPROVED_EXCESS(113, "Found more than one approved revisions"),
	
	PART_BIN_NOT_FOUND(104, "Part bin not found"),
	
	PART_PLANT_INFO_NOT_FOUND(105, "Part plant information not found"),
	
	MATERIALS_NOT_FOUND(201, "Child parts not found"),
	
	WORK_CENTER_NONEXISTENT(501, "Work Center does not exist"),
	
	WORK_CENTERS_NOT_FOUND(502, "Work Centers not found"),
	
	JOB_OPS_NOT_FOUND(601, "Job opeartions not found"),
	
	ACTIVE_OPS_NOT_FOUND(602, "Active opeartions not found"),
	
	APP_SERVER_ERROR(-1, "Some error occured at application server"),
	
	BAD_SQL_ERROR(-2, "There is a bug in SQL statement. Find and burn that bug!!");
	
	private final int code;

	private final String message;
	

	private RestError(int code, String errorMesssage) {
		this.code = code;
		this.message = errorMesssage;
	}


	public int code() {
		return code;
	}

	public String message() {
		return message;
	}
	
	public static RestError valueOf(int code) {
		for (RestError eCode : values()) {
			if (eCode.code == code) {
				return eCode;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + code + "]");
	}
	
	public static String messageOf(RestError code) {
		for (RestError eCode : values()) {
			if (eCode == code) {
				return eCode.message;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + code + "]");
	}
	
}
