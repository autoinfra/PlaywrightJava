package listeners.MicrosoftTeams;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdaptiveCardReqBody{
	private List<AttachmentsItem> attachments;
	private String type;

	@Data
	@Builder
	public static class ActionsItem{
		private String style;
		private String type;
		private String title;
		private String url;
	}

	@Data
	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class AttachmentsItem{
		private String contentType;
		private Content content;
	}

	@Data
	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class BodyItem{
		private String type;
		private List<ItemsItem> items;
		private String id;
		private boolean isVisible;
		private String spacing;
		private List<FactsItem> facts;
		private String style;
		private String text;
	}

	@Data
	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public  static class ColumnsItem{
		private String width;
		private String type;
		private List<ItemsItem> items;
		private String spacing;
		private String verticalContentAlignment;
		private SelectAction selectAction;
	}

	@Data
	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Content{
		private String type;
		private List<BodyItem> body;
	}

	@Data
	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class FactsItem{
		private String title;
		private String value;
	}

	@Data
	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class ItemsItem{
		private List<ColumnsItem> columns;
		private String type;
		private String size;
		private String weight;
		private String style;
		private String text;
		private boolean wrap;
		private String altText;
		private String url;
		private String height;
		private String color;
		private String width;
		private String id;
		private boolean isVisible;
		private List<ItemsItem> items;
		private String data;
		private boolean isSubtle;
		private List<ActionsItem> actions;
	}

	@Data
	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class SelectAction{
		private List<String> targetElements;
		private String type;
	}
}