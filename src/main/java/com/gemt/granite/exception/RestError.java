package com.gemt.granite.exception;

public enum RestError {
		
	DATA_NOT_FOUND(100, "Data not found"),
	
	PART_NOT_FOUND(101, "Part not found"),
	
	PART_REVISION_NOT_FOUND(103, "Part revision not found"),
	
	PART_REVISION_APPROVED_EXCESS(105, "Found more than one approved revisions"),
	
	PART_BIN_NOT_FOUND(106, "Part bin not found"),
	
	PART_PLANT_INFO_NOT_FOUND(107, "Part plant information not found"),
	
	JOB_NOT_FOUND(108, "Job not found"),
	
	MATERIALS_NOT_FOUND(201, "Child parts not found"),
	
	JOB_DEMANDS_NOT_FOUND(202, "Job demands not found"),
		
	JOB_SUPPLY_NOT_FOUND(203, "Supplies not found"),
	
	ORDER_DEMANDS_NOT_FOUND(204, "Sales order demands not found"),

	PO_SUPPLY_NOT_FOUND(205, "POs not found"),

	PARENTS_NOT_FOUND(206, "Parents not found"),
	
	PENDING_INSPECTION_NOT_FOUND(207, "Pending inspection not found"),
	
	WORK_CENTER_NONEXISTENT(501, "Work Center does not exist"),
	
	WORK_CENTERS_NOT_FOUND(502, "Work Centers not found"),
	
	JOB_OPS_NOT_FOUND(601, "Job opeartions not found"),
	
	ACTIVE_OPS_NOT_FOUND(602, "Active opeartions not found");
	
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
