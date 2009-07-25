/*
 * $Header: /cvsroot/securityfilter/securityfilter/src/catalina/org/securityfilter/realm/catalina/CatalinaRealmAdapter.java,v 1.5 2004/01/26 09:31:13 maxcooper Exp $
 * $Revision: 1.5 $
 * $Date: 2004/01/26 09:31:13 $
 *
 * ====================================================================
 * The SecurityFilter Software License, Version 1.1
 *
 * (this license is derived and fully compatible with the Apache Software
 * License - see http://www.apache.org/LICENSE.txt)
 *
 * Copyright (c) 2002 SecurityFilter.org. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by
 *        SecurityFilter.org (http://www.securityfilter.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The name "SecurityFilter" must not be used to endorse or promote
 *    products derived from this software without prior written permission.
 *    For written permission, please contact license@securityfilter.org .
 *
 * 5. Products derived from this software may not be called "SecurityFilter",
 *    nor may "SecurityFilter" appear in their name, without prior written
 *    permission of SecurityFilter.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE SECURITY FILTER PROJECT OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 */

package org.securityfilter.realm.catalina;

import org.apache.catalina.*;
import org.securityfilter.realm.SecurityRealmInterface;

import java.security.Principal;

/**
 * CatalinaRealmAdapter - adapts a Catalina Realm for use with the securityfilter
 *
 * @author Max Cooper (max@maxcooper.com)
 * @version $Revision: 1.5 $ $Date: 2004/01/26 09:31:13 $
 */
public class CatalinaRealmAdapter implements SecurityRealmInterface {
   private Realm realm;

   /**
    * Set the Catalina Realm that we are to adapt.
    */
   public void setRealm(Object realm) {
      this.realm = (Realm) realm;
      try {
         if (realm instanceof Lifecycle) {
            ((Lifecycle) realm).start();
         }
      } catch (LifecycleException e) {
         e.printStackTrace();
      }
   }

   /**
    * Authenticate a user.
    *
    * @param username a username
    * @param password a plain text password, as entered by the user
    *
    * @return a Principal object representing the user if successful, false otherwise
    */
   public Principal authenticate(String username, String password) {
      return realm.authenticate(username, password);
   }

   /**
    * Test for role membership.
    *
    * Use Principal.getName() to get the username from the principal object.
    *
    * @param principal Principal object representing a user
    * @param rolename name of a role to test for membership
    * @return true if the user is in the role, false otherwise
    */
   public boolean isUserInRole(Principal principal, String rolename) {
      return realm.hasRole(principal, rolename);
   }
}

// ----------------------------------------------------------------------------
// EOF
