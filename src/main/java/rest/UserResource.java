package rest;

import entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import firebase.persistence.IFirebaseConection;
import firebase.persistence.IFirebasePersistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pesistence.implementation.FirebaseConectionImp;
import pesistence.implementation.FirebasePersistence;

@Path("user")
public class UserResource {

    @Context
    private UriInfo context;
    private String link = "https://finaleapp-dcad7.firebaseio.com";
    private String path = "C:\\\\Users\\\\MoK\\\\Documents\\\\NetBeansProjects\\\\Firebase\\\\finaleapp-dcad7-firebase-adminsdk-ultqu-62bc411e68.json";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    HttpHeaders headers;

    public UserResource() {

    }

    @Path("getuser/{userName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@PathParam("userName") String userName) {
        IFirebaseConection IFC = new FirebaseConectionImp();
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String username, String entity) {
        
        
        
        
        return null;
        //IFirebaseConection IFC = new FirebaseConectionImp();
        //IFC.initFirebase(link, path);
        //IFirebasePersistence firebase = new FirebasePersistence();
        //User pd = gson.fromJson(entity, User.class);
        //return Response
               // .status(Response.Status.CREATED)
                //.entity(gson.toJson(pd))
                //.build();
    }
    
        @DELETE
    @Path("delete/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("userId") String userId)
    {
      IFirebaseConection IFC = new FirebaseConectionImp();
        IFC.initFirebase(link, path);
        IFirebasePersistence firebase = new FirebasePersistence();

        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(firebase.deleteUser(userId)))
                .build();
    }


}
