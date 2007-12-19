package com.stimulus.archiva.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.stimulus.util.Compare;

public class Permission implements java.io.Serializable  {
	
	public Permission() {
		List<String> roles = Identity.getRoles();
		for (String role : roles) {
			if (!Compare.equalsIgnoreCase(role, "none"))
				setPermission(role,true,true,true,true,true,true);
		}
	}

	Hashtable<String,PermissionRoleMap> permissions = new Hashtable<String,PermissionRoleMap>();
	
	
	public PermissionRoleMap getPermission(String role) {
		return permissions.get(role);
	}
	
	public List<PermissionRoleMap> getPermissions() {
		List<PermissionRoleMap> list = new ArrayList<PermissionRoleMap>();
		list.addAll(permissions.values());
		return list;
	}
	
	public void setPermissions(List<PermissionRoleMap> permissions) { 
		for (PermissionRoleMap permission: permissions) {
			this.permissions.put(permission.getRole(),permission);
		}
	}
    
    public void setPermission(String role, boolean allowDelete, boolean allowView, boolean allowPrint, boolean allowExport, boolean allowSave, boolean allowSend) {
    	permissions.put(role, new PermissionRoleMap(role, allowDelete,allowView,allowPrint,allowExport,allowSave,allowSend));
    }
    
    public void setPermission(PermissionRoleMap permission) {
    	permissions.put(permission.getRole(),permission);
    }
    
    public class PermissionRoleMap implements Serializable {
    
    	/**
		 * 
		 */
		private static final long serialVersionUID = -1109120011867781753L;
		protected String role;
		protected boolean allowDelete;
		protected boolean allowView;
		protected boolean allowPrint;
		protected boolean allowExport;
		protected boolean allowSave;
		protected boolean allowSend;
		

	  	public PermissionRoleMap(String role, boolean allowDelete, boolean allowView, boolean allowPrint, boolean allowExport, boolean allowSave, boolean allowSend) {
	  		setRole(role);
	  		setAllowDelete(allowDelete);
	  		setAllowView(allowView);
	  		setAllowPrint(allowPrint);
	  		setAllowExport(allowExport);
	  		setAllowSave(allowSave);
	  		setAllowSend(allowSend);
	  	}

		public void setRole(String role) { 
			this.role = role;
		}
		
		public String getRole() { 
			return role;
		}
		

		public void setAllowDelete(boolean allowDelete) {
			this.allowDelete = allowDelete;
		}
		public boolean getAllowDelete() {
			return allowDelete;
		}
		
		public void setAllowView(boolean allowView) {
			this.allowView = allowView;
		}
		
		public boolean getAllowView() { return allowView; }
		
		public boolean getAllowPrint() { return allowPrint; }
		
		public void setAllowPrint(boolean allowPrint) {
			this.allowPrint = allowPrint;
		}
		
		public boolean getAllowExport() { return allowExport; }
		
		public void setAllowExport(boolean allowExport) {
			this.allowExport = allowExport;
		}
		
		public boolean getAllowSave() { return allowSave; }
		
		public void setAllowSave(boolean allowSave) {
			this.allowSave = allowSave;
		}
		
		public boolean getAllowSend() { return allowSend; }
		
		public void setAllowSend(boolean allowSend) {
			this.allowSend = allowSend;
		}
		
		

    }
}
