/* Copyright (C) 
 * 2019 - Nicholas Siviglia
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * 
 */
/**
 * @file SpringSecurityConfig.java
 * @brief very basic auth for blog.
 * @author Nicholas Siviglia
 * @version 1.0-SNAPSHOT
 * @date 2019-03-01
 */

package com.siviglia.web;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    //Permit anyone to access the blog posts and block everything else using
    //basic auth.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v2/blog/").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v2/blog/id/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v2/blog/after/**").permitAll()
				.anyRequest().authenticated()
				.and().httpBasic();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
            .withUser("user")
            .password("{noop}password")
            .roles("USER");
	}

}
