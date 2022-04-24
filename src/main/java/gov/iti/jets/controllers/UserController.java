package gov.iti.jets.controllers;

import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.domain.dtos.User.UserGetDto;
import gov.iti.jets.domain.dtos.User.UserPatchDto;
import gov.iti.jets.domain.dtos.User.UserPostDto;
import gov.iti.jets.domain.dtos.User.UserPutDto;
import gov.iti.jets.domain.dtos.util.UserMapper;
import gov.iti.jets.service.impl.UserServiceImpl;
import gov.iti.jets.service.interfaces.UserServiceInt;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path( "users" )
public class UserController {
    UserServiceInt usi = new UserServiceImpl();

    @GET
    @Produces( {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
    public List<UserGetDto> getAllUsers() {
        List<UserGetDto> getDtos = new ArrayList<>();
        usi.getUsers().forEach( user -> getDtos.add( UserMapper.entityToGet( user ) ) );
        return getDtos;
    }

    @GET
    @Path( "{cid}" )
    @Produces( {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
    public Response getUser( @PathParam( "cid" ) int id ) {
        UserGetDto getDto = UserMapper.entityToGet( usi.getUser( id ) );
        if ( getDto != null ) {
            return Response.ok().entity( getDto ).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes( {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
    public Response createUser( UserPostDto postDto ) {
        String res = usi.createUser( UserMapper.postToEntity( postDto ) );
        if ( res != null ) {
            return Response.ok().entity( res ).build();
        } else {
            return Response.notModified().build();
        }
    }

    @PUT
    @Consumes( {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
    public Response updatePutUser( UserPutDto putDto ){
        String res =usi.updateUser( UserMapper.putToEntity( putDto ) );
        if ( res != null ) {
            return Response.ok().entity( res ).build();
        } else {
            return Response.notModified().build();
        }
    }

    @PATCH
    @Consumes( {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
    public Response updatePatchUser( UserPatchDto patchDto ){
        String res =usi.updateUser( UserMapper.patchToEntity( patchDto ) );
        if ( res != null ) {
            return Response.ok().entity( res ).build();
        } else {
            return Response.notModified().build();
        }
    }

    @DELETE
    @Path( "{cid}" )
    public Response deleteUser( @PathParam( "cid" ) int id ) {
        return Response.ok().entity( usi.deleteUser( id ) ).build();
    }

}
