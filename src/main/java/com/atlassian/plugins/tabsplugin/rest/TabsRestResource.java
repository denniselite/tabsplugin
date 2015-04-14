package com.atlassian.plugins.tabsplugin.rest;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import shared-access-layer api
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.atlassian.activeobjects.external.ActiveObjects;

//import my entity interface
import com.atlassian.plugins.tabsplugin.data.TabsAo;

@Path("/row")
@Produces({MediaType.APPLICATION_JSON})
@Consumes ({ MediaType.APPLICATION_JSON })
public class TabsRestResource{

	private final ActiveObjects ao;

	public TabsRestResource(ActiveObjects ao){
		this.ao = checkNotNull(ao);
	}

	//GET by ID method for restfull-table
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getRow(final @PathParam("id") int id){

		//outRow for response
		final TabsRestResourceModel outRow = new TabsRestResourceModel();
		ao.executeInTransaction(new TransactionCallback<Void>(){
			@Override
			public Void doInTransaction()
			{
				//Searching and setting fields for outRow
				TabsAo tabsao = ao.get(TabsAo.class, id);
				outRow.setId(id);
				outRow.setDate(tabsao.getDate());
				outRow.setString(tabsao.getString());
				return null;
			}
		});
		return Response.ok(outRow).build();
	}

	//GET all rows method for restfull-table
	@GET
	@Path("/all")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAll(){

		//Result List for all entities
		final ArrayList<TabsRestResourceModel> allRows = new ArrayList<TabsRestResourceModel>();
		ao.executeInTransaction(new TransactionCallback<Void>(){
			@Override
			public Void doInTransaction(){

				//Search & add rows to result list
				for (TabsAo tabsao : ao.find(TabsAo.class)){
					TabsRestResourceModel tmpRow = new TabsRestResourceModel();
					tmpRow.setId(tabsao.getID());
					tmpRow.setDate(tabsao.getDate());
					tmpRow.setString(tabsao.getString());
					allRows.add(tmpRow);
				}
				return null;
			}
		});
		return Response.ok(allRows).build();
	}

	//POST method for restfull table - creating new row
	@POST
	@Consumes ({ MediaType.APPLICATION_JSON })
	public Response createRow(final TabsRestResourceModel row){

		//Check not-null params
		if ((row.getString() != null)&&(row.getDate() != null)&&(row.getString() != "")&&(row.getDate() != "")){
			final TabsAo tabsao = ao.create(TabsAo.class);
			ao.executeInTransaction(new TransactionCallback<TabsAo>(){
				@Override
				public TabsAo doInTransaction(){

					//Set & save entity
					tabsao.setString(row.getString()); 
					tabsao.setDate(row.getDate());
					tabsao.save();
					return tabsao;
				}
			});
			row.setId(tabsao.getID());

			//return result
			return Response.ok(row).build();
		} else {
			return Response.status(400).build();
		}
	}

	//PUT method for restfull table - update row by ID
	@PUT
	@Path ("{id}")
	public Response updateRow(final @PathParam("id") int id, final TabsRestResourceModel row){

		//Check input params
		if ( ( ((row.getString() != null)&&(row.getString() != "")) || ((row.getDate() != null)&&(row.getDate() != ""))) && (id >= 0) ){
			ao.executeInTransaction(new TransactionCallback<TabsAo>(){
				@Override
				public TabsAo doInTransaction(){
					TabsAo tabsao = ao.get(TabsAo.class, id);

					//if string not empty, I set current value else I set entity value to input row
					if (row.getString() != null){
						tabsao.setString(row.getString());
					} else {
						row.setString(tabsao.getString());
					}

					//if date not empty, I set current value else I set entity value to input row
					if (row.getDate() != null){
						tabsao.setDate(row.getDate());
					} else {
						row.setDate(tabsao.getDate());
					}
					row.setId(id);
					tabsao.save();
					return tabsao;
				}
			});
			return Response.ok(row).build();
		} else {
			return Response.status(400).build();
		}
	}

	//DELETE method for restfull table - delete row by ID
	@DELETE
	@Path("/{id}")
	public Response deleteRow(final @PathParam("id") int id){
		ao.executeInTransaction(new TransactionCallback<Void>(){
			@Override
			public Void doInTransaction()
			{
				//Search & delete entity by ID
				TabsAo tabsao = ao.get(TabsAo.class, id);
				ao.delete(tabsao);
				return null;
			}
		});
		
		//return 200-OK blank response
		return Response.ok().build();
	}

}