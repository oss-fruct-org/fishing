// import QtQuick 1.0 // to target S60 5th Edition or Maemo 5
import QtQuick 1.1
import com.nokia.meego 1.0
import "UIConstants.js" as UI

Page {
    tools: lakeTools
    orientationLock: PageOrientation.LockPortrait
    property string lake_name

    Header {
        id: fishHeader
        anchors.top: parent.top
        title: lakeDescription.title
    }

    XmlListModel {
        id: fishModel
        XmlRole { name: "name"; query: "@name/string()"; }
        XmlRole { name: "fish_info"; query: "string()"; }
    }

    onLake_nameChanged: {
        fishModel.source = "fishinfo.xml"
        fishModel.query = "/fishinfo/item[@lake='"+ lake_name +"']/fish"
    }

    ListView {
        id: fishView
        anchors.top: fishHeader.bottom
        anchors.left: parent.left
        anchors.right: parent.right
        anchors.bottom: parent.bottom

        model: fishModel
        delegate: FishDelegate {
            is_content: (fish_info.trim() !== "")
            onDelegateClicked: {
                fishDescription.title = name
                fishDescription.description = fish_info.trim()
                pageStack.push(fishDescription)
            }
        }
    }
}

