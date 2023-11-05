package com.enzosagnelonge.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
public class Goods {
    UUID id;
    String referenceType; 
    String reference; 
    int quantity; 
    double weight;
    int quantityTotalReference; 
    double weightTotalReference; 
    String description;

    public boolean isReferenceValid(){
        return !referenceType.equals("AWB") || reference.length() == 11;
    }

    public boolean isQuantityValid(){
        return quantityTotalReference >= quantity;
    }

    public boolean isWeightValid(){
        return weightTotalReference >= weight;
    }

    public Element toXML(Document doc) throws ParserConfigurationException {
        Element rootElement = doc.createElement("goods");

        // REFERENCE
        Element refNode = doc.createElement("ref");
        refNode.setAttribute("type", this.referenceType);
        refNode.setAttribute("code", this.reference);
        rootElement.appendChild(refNode);

        // AMOUNT
        Element amountNode = doc.createElement("amount");
        amountNode.setAttribute("quantity", String.valueOf(this.quantity));
        amountNode.setAttribute("weight", String.valueOf(this.weight));
        rootElement.appendChild(amountNode);

        // DESCRIPTION
        Element descriptionNode = doc.createElement("description");
        descriptionNode.appendChild(doc.createTextNode(this.description));
        rootElement.appendChild(descriptionNode);

        // TOTAL REF AMOUNT
        Element totalRefAmountNode = doc.createElement("totalRefAmount");
        totalRefAmountNode.setAttribute("quantity", String.valueOf(this.quantityTotalReference));
        totalRefAmountNode.setAttribute("weight", String.valueOf(this.weightTotalReference));
        rootElement.appendChild(totalRefAmountNode);

        return rootElement;
    }
}
