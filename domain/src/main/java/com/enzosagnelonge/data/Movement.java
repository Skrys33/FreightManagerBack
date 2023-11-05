package com.enzosagnelonge.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.xml.parsers.ParserConfigurationException;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Movement {
    public enum TypeMovement {
        In,
        Out
    }

    UUID id;
    LocalDateTime creationDateTime;
    String creationUser;
    LocalDateTime movementDateTime;
    String location;
    Goods goods;
    Warehouse fromWarehouse;
    Warehouse toWarehouse;
    String customsStatus;
    String referenceAuthorization;
    String typeAuthorization;
    TypeMovement typeMovement;


    // TODO : MOVE TO UTILS
    public Element toXML(Document doc) throws ParserConfigurationException {
        Element rootElement = doc.createElement("CargoMessage");
        rootElement.setAttribute("type", "WarehouseMovement-" + this.typeMovement);
        doc.appendChild(rootElement);

        // Header
        Element headerNode = doc.createElement("Header");
        headerNode.setAttribute("from","RAPIDCARGO");
        headerNode.setAttribute("to", "CARGOINFO");
        headerNode.setAttribute("messageTime", this.creationDateTime.toString());
        headerNode.setAttribute("messageId", this.id.toString()); // TODO : REVIEW
        rootElement.appendChild(headerNode);

        // WAREHOUSE MOVEMENT
        Element movementNode = doc.createElement("WareHouseMovement" + this.typeMovement);
        rootElement.appendChild(movementNode);

            // MOVEMENT TIME
        Element movementDatTimeNode = doc.createElement("movementTime");
        movementDatTimeNode.appendChild(doc.createTextNode(this.movementDateTime.toString()));
        movementNode.appendChild(movementDatTimeNode);

        Warehouse declaredInWarehouse = null;
        Warehouse fromToWarehouse = null;
        String fromToLabel = "";
        switch (this.typeMovement){
            case In:
                declaredInWarehouse = this.toWarehouse;
                fromToWarehouse = this.fromWarehouse;
                break;
            case Out:
                declaredInWarehouse = this.fromWarehouse;
                fromToWarehouse = this.toWarehouse;
                break;
        }

        // DECLARE IN
        if (declaredInWarehouse != null){
            Element declareInNode = doc.createElement("declareIn");
            declareInNode.setAttribute("code", declaredInWarehouse.code);
            declareInNode.setAttribute("label", declaredInWarehouse.label);
            movementNode.appendChild(declareInNode);
        }

        // FROM / TO
        if (declaredInWarehouse != null) {
            Element fromNode = doc.createElement(fromToLabel);
            fromNode.setAttribute("code", fromToWarehouse.code);
            fromNode.setAttribute("label", fromToWarehouse.label);
            movementNode.appendChild(fromNode);
        }

            // GOODS
        movementNode.appendChild(this.goods.toXML(doc));

            // CUSTOMS STATUS
        Element customsStatusNode = doc.createElement("customsStatus");
        customsStatusNode.appendChild(doc.createTextNode(this.customsStatus));
        movementNode.appendChild(customsStatusNode);


        return rootElement;
    }


}
