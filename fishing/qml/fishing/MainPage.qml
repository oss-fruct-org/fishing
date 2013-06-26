import QtQuick 1.1
import com.nokia.meego 1.0
import QtMobility.location 1.2

Page {
    tools: commonTools
    orientationLock: PageOrientation.LockPortrait

    ToolBarLayout {
        id: commonTools
        visible: true

        ToolIcon {
            platformIconId: "toolbar-back"
            anchors.left: (parent === undefined) ? undefined : parent.left
            onClicked: appWindow.pageStack.depth <= 1 ? Qt.quit() : appWindow.pageStack.pop()
        }

        ToolIcon {
            platformIconId: "toolbar-list"
            onClicked: {
                pageStack.push(placemarksView)
            }
        }

        ToolIcon {
            platformIconId: "toolbar-view-menu"
            anchors.right: (parent === undefined) ? undefined : parent.right
            onClicked: (myMenu.status === DialogStatus.Closed) ? myMenu.open() : myMenu.close()
        }
    }

    LakeDescription {
        id: lakeDescription
        property real lat
        property real lng

        ToolBarLayout {
            id: lakeTools
            visible: true
            property bool isReturnToMap: true

            function checkFirst() {
                descrBtn.checked = true
            }

            ToolIcon {
                platformIconId: "toolbar-back"
                anchors.left: (parent === undefined) ? undefined : parent.left
                onClicked: lakeTools.isReturnToMap ? pageStack.pop(mainPage) : pageStack.pop(placemarksView)
            }

            ButtonRow {
                TabButton {
                    id: descrBtn
                    text: "Описание"
                    onClicked: {
                        pageStack.push(lakeDescription)
                    }
                }

                TabButton {
                    text: "Рыбы"
                    onClicked: {
                        lakeFishes.lake_name = lakeDescription.title
                        pageStack.push(lakeFishes)
                    }
                }
            }

            ToolIcon {
                iconSource: "qrc:/gfx/location.png"
                onClicked: {
                    pageStack.pop(mainPage)
                    map.zoomLevel = 10
                    map.moveMap(lakeDescription.lat, lakeDescription.lng)
                }
            }


            ToolIcon {
                platformIconId: "toolbar-view-menu"
                anchors.right: (parent === undefined) ? undefined : parent.right
                onClicked: (myMenu.status === DialogStatus.Closed) ? myMenu.open() : myMenu.close()
            }
        }
    }

    LakeFishes { id: lakeFishes }
    FishDescription { id: fishDescription }
    ShopDescription { id: shopDescription }
    HostelDescription { id: hostelDescription }
    PlacemarksView { id: placemarksView }

    Header {
        id: header
        anchors.top: parent.top
        title: "Рыбалка в Карелии"
        iconSource: "qrc:/gfx/fish_icon.png"
    }

    XmlListModel {
        id: lakesModel
        XmlRole { name: "name"; query: "name/string()" }
        XmlRole { name: "description"; query: "description/string()" }
        XmlRole { name: "lat"; query: "latitude/string()" }
        XmlRole { name: "lng"; query: "longitude/string()" }
        XmlRole { name: "mark_type"; query: "@type/string()" }

        XmlRole { name: "address"; query: "address/string()" }
        XmlRole { name: "phone"; query: "phone/string()" }
        XmlRole { name: "site"; query: "site/string()" }

        onStatusChanged: {
            if (status == XmlListModel.Ready) {
                map.itemsModel = lakesModel;
            }
        }
    }

    MyMap {
        id: map
        anchors.top: header.bottom
        anchors.left: parent.left
        anchors.right: parent.right
        anchors.bottom: parent.bottom

        onShowMapMenu: {
            marksTypesDialog.open()
        }
    }

    SelectionDialog {
        id: marksTypesDialog

        titleText: ""
        selectedIndex: 0
        model: ListModel {
            ListElement { name: "Все" }
            ListElement { name: "Озера" }
            ListElement { name: "Рыболовные магазины" }
            ListElement { name: "Базы отдыха" }
        }

        onSelectedIndexChanged: {
            if (selectedIndex == 0) {
                lakesModel.query = "//placemark"
            } else if (selectedIndex == 1) {
                lakesModel.query = "//placemark[@type='lake']"
            } else if (selectedIndex == 2) {
                lakesModel.query = "//placemark[@type='shop']"
            } else {
                lakesModel.query = "//placemark[@type='hostel']"
            }
        }
    }

    Component.onCompleted: {
        lakesModel.source =  "lakes.xml";
        lakesModel.query = "//placemark";
    }
}
