// import QtQuick 1.0 // to target S60 5th Edition or Maemo 5
import QtQuick 1.1
import com.nokia.meego 1.0
import "UIConstants.js" as UI

Page {
    tools: marksTools
    orientationLock: PageOrientation.LockPortrait
    property string lake_name
    property bool isSearch: false

    PlacemarksHeader {
        id: placemarksHeader
        anchors.top: parent.top
        title: "Все"

        onTypeChanged: {
            if (typeName === "Все") {
                lakesModel.query = "//placemark"
                return;
            }

            var type_name = (typeName === "Озера") ? "lake" : ((typeName === "Рыболовные магазины") ? "shop" : "hostel")
            lakesModel.query = "//placemark[@type='" + type_name + "']"
        }
    }

    ToolBarLayout {
        id: marksTools

        ToolIcon {
            platformIconId: "toolbar-back"
            anchors.left: (parent === undefined) ? undefined : parent.left
            onClicked: {
                lakesModel.query = search.baseQuery;
                appWindow.pageStack.depth <= 1 ? Qt.quit() : appWindow.pageStack.pop();
            }
        }

        ToolIcon {
            platformIconId: "toolbar-search"
            onClicked: {
                search.baseQuery = lakesModel.query
                search.toggle()
            }
        }

        ToolIcon {
            platformIconId: "toolbar-refresh"
            enabled: isSearch
            onClicked: {
                isSearch = false
                lakesModel.query = "//placemark"
                placemarksHeader.reset()
            }
        }

        ToolIcon {
            platformIconId: "toolbar-view-menu"
            anchors.right: (parent === undefined) ? undefined : parent.right
            onClicked: (myMenu.status === DialogStatus.Closed) ? myMenu.open() : myMenu.close()
        }
    }

    Component {
        id: sectionHeading

        Item {
            width: parent.width
            height: 40

            Text {
                font.pixelSize: UI.FONT_LARGE
                text: (section == "lake") ? "Озера" : (section == "shop") ? "Магазины" : "Базы отдыха"
                color: "#1080DD"
                anchors.bottom: divider.top
                anchors.left: parent.left
                anchors.leftMargin: 6
            }

            Rectangle {
                id: divider
                width: parent.width
                anchors.bottom: parent.bottom
                color: "black"
                height: 1
            }
        }
    }

    ListView {
        id: marksView
        anchors.top: placemarksHeader.bottom
        anchors.left: parent.left
        anchors.right: parent.right
        anchors.bottom: parent.bottom

        model: lakesModel
        delegate: PlacemarkDelegate {
            onDelegateClicked: {
                if (mark_type == "lake") {
                    lakeTools.isReturnToMap = false;
                    lakeTools.checkFirst()
                    lakeDescription.lat = lat;
                    lakeDescription.lng = lng;
                    lakeDescription.title = name
                    lakeDescription.description = description.trim()
                    pageStack.push(lakeDescription)
                } else if (mark_type == "shop") {
                    shopDescription.lat = lat;
                    shopDescription.lng = lng;
                    shopDescription.title = name;
                    shopDescription.address = address;
                    shopDescription.phone = phone;
                    shopDescription.site = site;
                    pageStack.push(shopDescription)
                } else {
                    hostelDescription.lat = lat;
                    hostelDescription.lng = lng;
                    hostelDescription.title = name;
                    hostelDescription.description = description.trim();
                    hostelDescription.phone = phone;
                    hostelDescription.site = site;
                    pageStack.push(hostelDescription)
                }
            }
        }

        MouseArea {
            anchors.fill: parent
            enabled: search.show

            onClicked: {
                search.show = false;
                search.hideSearch();
            }
        }

        Text {
            id: emptyText
            visible: marksView.count == 0
            anchors.centerIn: parent
            font.pixelSize: UI.FONT_XLARGE
            smooth: true
            font.bold: true
            text: "Меток не найдено"
            opacity: 0.6
        }

        section.property: "mark_type"
        section.criteria: ViewSection.FullString
        section.delegate: placemarksHeader.title == "Все" ? sectionHeading : null
    }

    SearchField {
        id: search
        anchors.left: parent.left
        anchors.right: parent.right
        anchors.bottom: parent.bottom

        onNameFilterChanged: {
            if (filterName !== "") {
                isSearch = true;
                lakesModel.query = search.baseQuery;
                lakesModel.query += "[contains(lower-case(name),lower-case('" + filterName + "'))]";
            } else {
                isSearch = false;
                lakesModel.query = search.baseQuery;
            }
        }

        Connections {
            target: mainPage
            onStatusChanged: {
                if (status === PageStatus.Activating ) {
                    search.searchText = "";
                    isSearch = false;
                    marksView.focus = true;
                }
            }
        }
    }
}
