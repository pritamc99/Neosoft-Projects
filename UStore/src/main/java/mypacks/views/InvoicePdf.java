package mypacks.views;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;


import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;


import mypacks.model.MyOrder;
import mypacks.model.OrderItem;

@Component
public class InvoicePdf extends AbstractPdfView
{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		HttpSession session=request.getSession();
		MyOrder order=(MyOrder) session.getAttribute("order");
		
		Paragraph header = new Paragraph(new Chunk("Order Invoice",FontFactory.getFont(FontFactory.HELVETICA, 24,Font.BOLD)));
		header.setAlignment(Element.ALIGN_CENTER);
		
		Paragraph line=new Paragraph(new Chunk(new LineSeparator()));
		
		Paragraph toDetail1 = new Paragraph(new Chunk("Customer Name     : "+order.getUser().getFirstName()+" "+order.getUser().getLastName()));
		Paragraph toDetail2 = new Paragraph(new Chunk("Contact Number     : "+order.getUser().getMobileNumber()));
		Paragraph toDetail3 = new Paragraph(new Chunk("Email                      : "+order.getUser().getEmailId()));
		Paragraph toDetail4 = new Paragraph(new Chunk("Delivery Address    : "+order.getDeliveryAddress().getDeliveryAddress()));
		Paragraph toDetail5 = new Paragraph(new Chunk("Order Date             : "+order.getOrderDate()));
		Paragraph toDetail6 = new Paragraph(new Chunk("Exp.Delivery Date  : "+order.getExpectedDeliveryDate())	);
		
		PdfPTable table=new PdfPTable(5);
		table.setSpacingBefore(50.0f);
		table.setWidthPercentage(100.0f);
		PdfPCell cell1=new PdfPCell(new Paragraph("Sr.No"));
		PdfPCell cell2=new PdfPCell(new Paragraph("Item"));
		PdfPCell cell3=new PdfPCell(new Paragraph("Price"));
		PdfPCell cell4=new PdfPCell(new Paragraph("Quantity"));
		PdfPCell cell5=new PdfPCell(new Paragraph("Amount"));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell1);
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell2);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell3);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell4);
		cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell5);
		List<OrderItem> list=order.getOrderItem();
		int i=1;
		for(OrderItem item:list)
		{
			PdfPCell forcell1=new PdfPCell(new Paragraph(Integer.toString(i++)));
			PdfPCell forcell2=new PdfPCell(new Paragraph(item.getItem().getItemName()));
			PdfPCell forcell3=new PdfPCell(new Paragraph(Integer.toString(item.getItem().getPrice())));
			PdfPCell forcell4=new PdfPCell(new Paragraph(Long.toString(item.getQuantity())));
			PdfPCell forcell5=new PdfPCell(new Paragraph(Long.toString(item.getSubTotal())));
			forcell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(forcell1);
			
			table.addCell(forcell2);
			forcell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(forcell3);
			forcell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(forcell4);
			forcell5.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(forcell5);
		}
		i=0;
		PdfPCell finalcell1=new PdfPCell(new Paragraph(""));
		PdfPCell finalcell2=new PdfPCell(new Paragraph(""));
		PdfPCell finalcell3=new PdfPCell(new Paragraph(""));
		PdfPCell finalcell4=new PdfPCell(new Paragraph("Total"));
		PdfPCell finalcell5=new PdfPCell(new Paragraph(Integer.toString(order.getOrderAmount())));
		table.addCell(finalcell1);
		table.addCell(finalcell2);
		table.addCell(finalcell3);
		finalcell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(finalcell4);
		finalcell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(finalcell5);
		
		document.add(header);
		document.add(line);
		document.add(toDetail1);
		document.add(toDetail2);
		document.add(toDetail3);
		document.add(toDetail4);
		document.add(toDetail5);
		document.add(toDetail6);
		document.add(table);
		document.addTitle("Order Invoice");

	}

}
