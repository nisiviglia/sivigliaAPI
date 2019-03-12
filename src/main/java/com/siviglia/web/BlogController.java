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
 * @file BlogController.java
 * @brief Controller for sending/receiving blog posts. 
 * @author Nicholas Siviglia
 * @version 1.0-SNAPSHOT
 * @date 2019-03-01
 */

package com.siviglia.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@RestController 
public class BlogController{

    @Autowired
    private PostRepository repository;

    //Find the first ten visible posts and return without body text.
    @RequestMapping(value = "/api/v2/blog", 
        method = RequestMethod.GET, 
        produces = "application/json")     
    public @ResponseBody List<PostExcludeText> findFirst10PostsByVisibleIsTrue(){

        return repository.findFirst10ByVisibleIsTrue();
    }

    //Create or modify a post.
    @RequestMapping(value = "/api/v2/blog", 
        method = RequestMethod.PUT,
        produces = "application/json")
    public @ResponseBody Post createOrModifyPost(@RequestBody Post post){
        
        post.date = new Date().getTime(); 
        return repository.save(post); 
    }

    //Delete a post.
    @RequestMapping(value = "/api/v2/blog", 
        method = RequestMethod.DELETE,
        produces = "application/json")
    public @ResponseBody void deletePost(@RequestBody Post post){

        repository.deleteById(post.id);
        return;
    }

    //Find blog post by id.
    @RequestMapping(value = "/api/v2/blog/id/{id}", 
        method = RequestMethod.GET, 
        produces = "application/json")     
    public @ResponseBody Post 
        getBlogPostByIdAndIsVisible(@PathVariable(value="id") String id){

        return repository.findFirstByIdAndVisibleIsTrue(id);
    }

    //Find the next ten visible post after the date.
    @RequestMapping(value = "/api/v2/blog/after/{date}", 
        method = RequestMethod.GET, 
        produces = "application/json")     
    public @ResponseBody List<PostExcludeText> 
        findFirst10ByDateAfterAndVisibleIsTrue(@PathVariable(value="date") String strDate){
            
            //if the date is invalid, return an empty list;
            long longDate;
            try{
                longDate = Long.parseLong(strDate, 10); 
            }
            catch(NumberFormatException e){
                return new ArrayList<PostExcludeText>();
            }

        return repository.findFirst10ByDateAfterAndVisibleIsTrue( longDate );
    }

}
