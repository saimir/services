package org.collectionspace.services.client;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.collectionspace.services.common.authorityref.AuthorityRefList;
import org.collectionspace.services.jaxb.AbstractCommonList;
import org.collectionspace.services.client.BlobClient;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;

/**
 * @version $Revision: 2108 $
 */
@Path(MediaClient.SERVICE_PATH + "/")
@Produces("application/xml")
@Consumes("application/xml")
public interface MediaProxy extends CollectionSpaceProxy {

    //(C)reate
    @POST
    ClientResponse<Response> create(byte[] xmlPayload);
    
    @POST
    @Path("/{csid}")
    @Consumes("multipart/form-data")
    ClientResponse<Response> createBlobFromFormData(@PathParam("csid") String csid,
    		MultipartFormDataOutput formDataOutput);
    
    @POST
    @Path("/{csid}")
	@Produces("application/xml")
	@Consumes("application/xml")
    ClientResponse<Response>createBlobFromUri(@PathParam("csid") String csid,
    		@QueryParam(BlobClient.BLOB_URI_PARAM) String blobUri);

    //(R)ead
    @GET
    @Path("/{csid}")
    ClientResponse<String> read(@PathParam("csid") String csid);

    //(U)pdate
    @PUT
    @Path("/{csid}")
    ClientResponse<String> update(@PathParam("csid") String csid, byte[] xmlPayload);

    //(D)elete
    @DELETE
    @Path("/{csid}")
    ClientResponse<Response> delete(@PathParam("csid") String csid);
    
    // List
    @GET
    @Produces({"application/xml"})
    ClientResponse<AbstractCommonList> readList();

    // List Authority References
    @GET
    @Produces({"application/xml"})
    @Path("/{csid}/authorityrefs/")
    ClientResponse<AuthorityRefList> getAuthorityRefs(@PathParam("csid") String csid);
    
}
