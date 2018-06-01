/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;
import firebase.persistence.IFirebasePersistence;
import pesistence.implementation.FirebaseConectionImp;
import pesistence.implementation.FirebasePersistence;
import firebase.persistence.IFirebaseConnection;
import java.util.List;

/**
 * REST Web Service
 *
 * @author Lasse Andersen
 */
@Path("users")
public class UsersResource {

    @Context
    private UriInfo context;
    private String link = "https://finaleapp-dcad7.firebaseio.com";
    private String path = "C:\\\\Users\\\\MoK\\\\Documents\\\\NetBeansProjects\\\\Firebase\\\\finaleapp-dcad7-firebase-adminsdk-ultqu-62bc411e68.json";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    HttpHeaders headers;

    public UsersResource() {

    }

    @Path("getUser/{userName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@PathParam("userName") String userName) {
        IFirebaseConnection IFC = new FirebaseConectionImp();
        IFC.initFirebase(link, path);
        IFirebasePersistence firebase = new FirebasePersistence();
        return Response
                .status(200)
                .entity(gson.toJson(firebase.getUser(userName)))
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @Path("addUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String entity) {
        User user = gson.fromJson(entity, User.class);
        IFirebaseConnection IFC = new FirebaseConectionImp();
        IFC.initFirebase(link, path);
        IFirebasePersistence fireBase = new FirebasePersistence();
        boolean success = fireBase.addUser(user);
        if (success == true) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.CONFLICT).build();

    }

    @DELETE
    @Path("delete/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("userId") String userId) {
        IFirebaseConnection IFC = new FirebaseConectionImp();
        IFC.initFirebase(link, path);
        IFirebasePersistence firebase = new FirebasePersistence();
        return Response.ok(gson.toJson(firebase.deleteUser(userId))).build();
    }

    @Path("getAllUsers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {

        IFirebaseConnection IFC = new FirebaseConectionImp();
        IFC.initFirebase(link, path);
        IFirebasePersistence firebase = new FirebasePersistence();
        //Putting all users from firebase into a new List
        List<User> allUsers = firebase.getAllUsers();
        // Returns a Response 
        return Response
                .status(200)
                .entity(gson.toJson(allUsers))
                .build();

    }

}
