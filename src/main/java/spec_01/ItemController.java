package spec_01;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemController {
	
	private ItemRepository itemRepository = ItemRepository.getInstance();
	
	@GET
	public List<Item> selectAllItems() {
		return itemRepository.selectAllItems();
	}
	
	@POST
	public int insertItem(Item item) {
		return itemRepository.insertItem(item);
	}

}
