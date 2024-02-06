package com.microservice.systemadministration.utils.constants;

import com.microservice.systemadministration.business.vo.enums.UserRoleEnum;

public final class SystemAdministrationConstants {

    public static final String USER_SEQUENCE_ID = "user_sequence_id";
    public static final String ROLE_SEQUENCE_ID = "role_sequence_id";
    public static final String DEFAULT_ADMINISTRATOR_USER_EMAIL = "admin@gmail.com";
    public static final String ADMINISTRATOR_ROLE_NAME = UserRoleEnum.ADMINISTRATOR.name();
    public static final String OWNER_ROLE_NAME = UserRoleEnum.OWNER.name();
    public static final String MANAGER_ROLE_NAME = UserRoleEnum.MANAGER.name();
    public static final String ASSISTANT_ROLE_NAME = UserRoleEnum.ASSISTANT.name();

    private SystemAdministrationConstants() {
        // Default Constructor.
    }

}
