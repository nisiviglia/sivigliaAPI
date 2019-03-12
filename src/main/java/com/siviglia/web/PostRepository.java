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
 * @file PostRepository.java
 * @brief MongoDB repository for blog posts.
 * @author Nicholas Siviglia
 * @version 1.0
 * @date 2019-03-01
 */

package com.siviglia.web;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String>{
    
    //Find first ten visible blog posts. Don't return the text.
    public List<PostExcludeText> findFirst10ByVisibleIsTrue();

    //Find the next ten visible blog posts after the supplied date. Don't return the text.
    public List<PostExcludeText> findFirst10ByDateAfterAndVisibleIsTrue(long date);
    
    //Find the visible post by id and return it.
    public Post findFirstByIdAndVisibleIsTrue(String id);
}



