package com.enzosagnelonge.services;

import com.enzosagnelonge.data.Goods;
import com.enzosagnelonge.data.Movement;
import com.enzosagnelonge.data.Warehouse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class XMLMovementConverterService {

    public static Element convertToXML(Document doc, Movement movement) {
        Element rootElement = doc.createElement("CargoMessage");
        rootElement.setAttribute("type", "WarehouseMovement-" + movement.getTypeMovement());
        doc.appendChild(rootElement);

        // Header
        Element headerNode = doc.createElement("Header");
        headerNode.setAttribute("from","RAPIDCARGO");
        headerNode.setAttribute("to", "CARGOINFO");
        headerNode.setAttribute("messageTime", movement.getCreationDateTime().toString());
        headerNode.setAttribute("messageId", movement.getId().toString()); // TODO : REVIEW
        rootElement.appendChild(headerNode);

        // WAREHOUSE MOVEMENT
        Element movementNode = doc.createElement("WareHouseMovement" + movement.getTypeMovement());
        rootElement.appendChild(movementNode);

        // MOVEMENT TIME
        Element movementDatTimeNode = doc.createElement("movementTime");
        movementDatTimeNode.appendChild(doc.createTextNode(movement.getMovementDateTime().toString()));
        movementNode.appendChild(movementDatTimeNode);

        Warehouse declaredInWarehouse = null;
        Warehouse fromToWarehouse = null;
        String fromToLabel = "";
        switch (movement.getTypeMovement()){
            case In:
                declaredInWarehouse = movement.getToWarehouse();
                fromToWarehouse = movement.getFromWarehouse();
                fromToLabel = "from";
                break;
            case Out:
                declaredInWarehouse = movement.getFromWarehouse();
                fromToWarehouse = movement.getToWarehouse();
                fromToLabel = "to";
                break;
        }

        // DECLARE IN
        if (declaredInWarehouse != null){
            Element declareInNode = doc.createElement("declareIn");
            declareInNode.setAttribute("code", declaredInWarehouse.getCode());
            declareInNode.setAttribute("label", declaredInWarehouse.getLabel());
            movementNode.appendChild(declareInNode);
        }

        // FROM / TO
        if (declaredInWarehouse != null) {
            Element fromNode = doc.createElement(fromToLabel);
            fromNode.setAttribute("code", fromToWarehouse.getCode());
            fromNode.setAttribute("label", fromToWarehouse.getLabel());
            movementNode.appendChild(fromNode);
        }

        // GOODS
        movementNode.appendChild(convertToXML(doc, movement.getGoods()));

        // CUSTOMS STATUS
        Element customsStatusNode = doc.createElement("customsStatus");
        customsStatusNode.appendChild(doc.createTextNode(movement.getCustomsStatus()));
        movementNode.appendChild(customsStatusNode);

        return rootElement;
    }


    public static Element convertToXML(Document doc, Goods goods) {
        Element rootElement = doc.createElement("goods");

        // REFERENCE
        Element refNode = doc.createElement("ref");
        refNode.setAttribute("type", goods.getReferenceType());
        refNode.setAttribute("code", goods.getReference());
        rootElement.appendChild(refNode);

        // AMOUNT
        Element amountNode = doc.createElement("amount");
        amountNode.setAttribute("quantity", String.valueOf(goods.getQuantity()));
        amountNode.setAttribute("weight", String.valueOf(goods.getWeight()));
        rootElement.appendChild(amountNode);

        // DESCRIPTION
        Element descriptionNode = doc.createElement("description");
        descriptionNode.appendChild(doc.createTextNode(goods.getDescription()));
        rootElement.appendChild(descriptionNode);

        // TOTAL REF AMOUNT
        Element totalRefAmountNode = doc.createElement("totalRefAmount");
        totalRefAmountNode.setAttribute("quantity", String.valueOf(goods.getQuantityTotalReference()));
        totalRefAmountNode.setAttribute("weight", String.valueOf(goods.getWeightTotalReference()));
        rootElement.appendChild(totalRefAmountNode);

        return rootElement;
    }


    public static String getXMlFile(Movement movement) throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        StringWriter xmlStringWriter = new StringWriter();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(convertToXML(doc, movement)), new StreamResult(xmlStringWriter));

        return xmlStringWriter.toString();
    }
}
