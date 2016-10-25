package TagCloud;
 
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudItem;
import org.primefaces.model.tagcloud.TagCloudModel;
 
@ManagedBean
public class TagCloudView {
     
    private TagCloudModel model;
     
    @PostConstruct
    public void init() {
        model = new DefaultTagCloudModel();
        model.addTag(new DefaultTagCloudItem("Ami1", 1));
        model.addTag(new DefaultTagCloudItem("Ami2", "#", 3));
        model.addTag(new DefaultTagCloudItem("Ami3", 2));
        model.addTag(new DefaultTagCloudItem("Ami4", "#", 5));
        model.addTag(new DefaultTagCloudItem("Ami5", 4));
        model.addTag(new DefaultTagCloudItem("Ami6", "#", 2));
        model.addTag(new DefaultTagCloudItem("Ami7", 5));
        model.addTag(new DefaultTagCloudItem("Ami8",  3));
        model.addTag(new DefaultTagCloudItem("Ami9", "#", 4));
        model.addTag(new DefaultTagCloudItem("Ami10", "#", 1));
        model.addTag(new DefaultTagCloudItem("Ami1", 1));
        model.addTag(new DefaultTagCloudItem("Ami2", "#", 3));
        model.addTag(new DefaultTagCloudItem("Ami3", 2));
        model.addTag(new DefaultTagCloudItem("Ami4", "#", 5));
        model.addTag(new DefaultTagCloudItem("Ami5", 4));
        model.addTag(new DefaultTagCloudItem("Ami6", "#", 2));
        model.addTag(new DefaultTagCloudItem("Ami7", 5));
        model.addTag(new DefaultTagCloudItem("Ami8",  3));
        model.addTag(new DefaultTagCloudItem("Ami9", "#", 4));
        model.addTag(new DefaultTagCloudItem("Ami10", "#", 1));
        model.addTag(new DefaultTagCloudItem("Ami1", 1));
        model.addTag(new DefaultTagCloudItem("Ami2", "#", 3));
        model.addTag(new DefaultTagCloudItem("Ami3", 2));
        model.addTag(new DefaultTagCloudItem("Ami4", "#", 5));
        model.addTag(new DefaultTagCloudItem("Ami5", 4));
        model.addTag(new DefaultTagCloudItem("Ami6", "#", 2));
        model.addTag(new DefaultTagCloudItem("Ami7", 5));
        model.addTag(new DefaultTagCloudItem("Ami8",  3));
        model.addTag(new DefaultTagCloudItem("Ami9", "#", 4));
        model.addTag(new DefaultTagCloudItem("Ami10", "#", 1));
               model.addTag(new DefaultTagCloudItem("Ami4", "#", 5));
        model.addTag(new DefaultTagCloudItem("Ami5", 4));
        model.addTag(new DefaultTagCloudItem("Ami6", "#", 2));
        model.addTag(new DefaultTagCloudItem("Ami7", 5));
        model.addTag(new DefaultTagCloudItem("Ami8",  3));
        model.addTag(new DefaultTagCloudItem("Ami9", "#", 4));
        model.addTag(new DefaultTagCloudItem("Ami10", "#", 1));
        model.addTag(new DefaultTagCloudItem("Ami1", 1));
        model.addTag(new DefaultTagCloudItem("Ami2", "#", 3));
        model.addTag(new DefaultTagCloudItem("Ami3", 2));
        model.addTag(new DefaultTagCloudItem("Ami4", "#", 5));
        model.addTag(new DefaultTagCloudItem("Ami5", 4));
        model.addTag(new DefaultTagCloudItem("Ami6", "#", 2));
        model.addTag(new DefaultTagCloudItem("Ami7", 5));
        model.addTag(new DefaultTagCloudItem("Ami8",  3));
        model.addTag(new DefaultTagCloudItem("Ami9", "#", 4));
        model.addTag(new DefaultTagCloudItem("Ami10", "#", 1));
               model.addTag(new DefaultTagCloudItem("Ami4", "#", 5));
        model.addTag(new DefaultTagCloudItem("Ami5", 4));
        model.addTag(new DefaultTagCloudItem("Ami6", "#", 2));
        model.addTag(new DefaultTagCloudItem("Ami7", 5));
        model.addTag(new DefaultTagCloudItem("Ami8",  3));
        model.addTag(new DefaultTagCloudItem("Ami9", "#", 4));
        model.addTag(new DefaultTagCloudItem("Ami10", "#", 1));
        model.addTag(new DefaultTagCloudItem("Ami1", 1));
        model.addTag(new DefaultTagCloudItem("Ami2", "#", 3));
        model.addTag(new DefaultTagCloudItem("Ami3", 2));
        model.addTag(new DefaultTagCloudItem("Ami4", "#", 5));
        model.addTag(new DefaultTagCloudItem("Ami5", 4));
        model.addTag(new DefaultTagCloudItem("Ami6", "#", 2));
        model.addTag(new DefaultTagCloudItem("Ami7", 5));
        model.addTag(new DefaultTagCloudItem("Ami8",  3));
        model.addTag(new DefaultTagCloudItem("Ami9", "#", 4));
        model.addTag(new DefaultTagCloudItem("Ami10", "#", 1));
               model.addTag(new DefaultTagCloudItem("Ami4", "#", 5));
        model.addTag(new DefaultTagCloudItem("Ami5", 4));
        model.addTag(new DefaultTagCloudItem("Ami6", "#", 2));
        model.addTag(new DefaultTagCloudItem("Ami7", 5));
        model.addTag(new DefaultTagCloudItem("Ami8",  3));
        model.addTag(new DefaultTagCloudItem("Ami9", "#", 4));
        model.addTag(new DefaultTagCloudItem("Ami10", "#", 1));
        model.addTag(new DefaultTagCloudItem("Ami1", 1));
        model.addTag(new DefaultTagCloudItem("Ami2", "#", 3));
        model.addTag(new DefaultTagCloudItem("Ami3", 2));
        model.addTag(new DefaultTagCloudItem("Ami4", "#", 5));
        model.addTag(new DefaultTagCloudItem("Ami5", 4));
        model.addTag(new DefaultTagCloudItem("Ami6", "#", 2));
        model.addTag(new DefaultTagCloudItem("Ami7", 5));
        model.addTag(new DefaultTagCloudItem("Ami8",  3));
        model.addTag(new DefaultTagCloudItem("Ami9", "#", 4));
        model.addTag(new DefaultTagCloudItem("Ami10", "#", 1));
    }
 
    public TagCloudModel getModel() {
        return model;
    }
     
    public void onSelect(SelectEvent event) {
        TagCloudItem item = (TagCloudItem) event.getObject();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", item.getLabel());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}