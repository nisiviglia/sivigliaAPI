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
 * @file Post.java
 * @brief Model for blog posts.
 * @author Nicholas Siviglia
 * @version 1.0-SNAPSHOT
 * @date 2019-03-01
 */

package com.siviglia.web;

import org.springframework.data.annotation.Id;

public class Post{

    @Id
    public String id;

    public long date;
    public String title;
    public String discr; //description
    public String text;
    public boolean visible;

    public Post() {}

    public Post(long date, String title, 
            String discr, String text, boolean visible){
    
        this.date = date;
        this.title = title;
        this.discr = discr;
        this.text = text;
        this.visible = visible;
    }

    @Override 
    public String toString(){
        
        return String.format("Post[id=%s, date='%d', title='%s', discription='%s', text='%s', visible=%b]", id, date, title, discr, text, visible);
    
    }

}
