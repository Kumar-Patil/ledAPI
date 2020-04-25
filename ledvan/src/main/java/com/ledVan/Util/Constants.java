package com.ledVan.Util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Santosh Patil
 */
public class Constants {

    public enum Status {
        Pending,
        Approved,
        NotApproved,
        Deleted;
    }

    public static String roleName(long roleId) {
        Map<Long, String> role = new HashMap<>();
        role.put(1L, "Super Admin");
        role.put(2L, "Admin");
        role.put(3L, "PanelUser");
        return role.get(roleId);
    }
}
