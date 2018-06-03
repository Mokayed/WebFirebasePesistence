package rest;

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
import object.User;
import persistence.implementation.FirebaseConnectionImp;
import persistence.implementation.FirebasePersistenceImp;
import interfaces.IFirebaseConnection;
import interfaces.IUser;
import java.util.List;
import interfaces.IUserPersistence;
import interfaces.IUserResource;

// We implements IUserResource Interface. 
@Path("users")
public class UsersResource implements IUserResource {

    @Context
    private UriInfo context;
    private String link = "https://finaleapp-dcad7.firebaseio.com";
    private String path = "C:\\\\Users\\\\MoK\\\\Desktop\\\\Datamatiker\\\\4.sem\\\\Advanced Prog\\\\Advancedeksame-project\\\\WebFirebasePesistence\\\\finaleapp-dcad7-firebase-adminsdk-ultqu-62bc411e68.json";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    HttpHeaders headers;

    public UsersResource() {

    }

    @Path("getUser/{userName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getUser(@PathParam("userName") String userName) {
        String notFound = "NOT FOUND";
        IFirebaseConnection IFC = new FirebaseConnectionImp();
        IFC.initFirebase(link, path);
        IUserPersistence firebase = new FirebasePersistenceImp();
        IUser user = firebase.getUser(userName);
        if (user == null) {
            //Returns a string with notFound 
            return Response.status(Response.Status.NOT_FOUND).entity(gson.toJson(notFound)).build();
        }
        return Response
                .status(200)
                .entity(gson.toJson(user))
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
    @Override
    public Response addUser(String entity) {
        IUser user = gson.fromJson(entity, User.class);
        IFirebaseConnection IFC = new FirebaseConnectionImp();
        IFC.initFirebase(link, path);
        IUserPersistence fireBase = new FirebasePersistenceImp();
        boolean success = fireBase.addUser(user);
        if (success == true) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.CONFLICT).build();

    }

    @DELETE
    @Path("delete/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response deleteUser(@PathParam("userId") String userId) {
        String notFound = "NOT FOUND";
        IFirebaseConnection IFC = new FirebaseConnectionImp();
        IFC.initFirebase(link, path);
        IUserPersistence firebase = new FirebasePersistenceImp();
        IUser user = firebase.deleteUser(userId);
        if (user == null) {
            //Returns a string with notFound 
            return Response.status(Response.Status.NOT_FOUND).entity(gson.toJson(notFound)).build();
        }
        return Response.ok(gson.toJson(user)).build();
    }

    @Path("getAllUsers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getAllUsers() {
        IFirebaseConnection IFC = new FirebaseConnectionImp();
        IFC.initFirebase(link, path);
        IUserPersistence firebase = new FirebasePersistenceImp();
        //Putting all users from firebase into a new List
        List<IUser> allUsers = firebase.getAllUsers();
        // Returns a Response 
        return Response
                .status(200)
                .entity(gson.toJson(allUsers))
                .build();

    }

}
