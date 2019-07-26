package com.stackroute.muzixapplication.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//created entity annotation
@Entity
@Data //annotated as data for getters and setters
@NoArgsConstructor //annotated as noargsconstructor for default constructor
@AllArgsConstructor //annotated as allargsconstructor for parameterized constructor with all fields
@ApiModel(description = "All details about the Music tracks. ")
public class Music {
    @Id
    @ApiModelProperty(notes = "The database generated track ID")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    int trackId; //annotated as id to create id as primary key
    @ApiModelProperty(notes = "The database generated track Name")
    String trackName; //created trackname and track comments
    @ApiModelProperty(notes = "The database generated track Comments")
    String trackComments;
}
