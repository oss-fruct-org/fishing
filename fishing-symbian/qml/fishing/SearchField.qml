import QtQuick 1.1
import com.nokia.symbian 1.1
import "UIConstants.js" as UI
import "constants.js" as ExtrasConstants

Rectangle {
    id: popupSearchField
    height: searchInput.height + 2 * 10
    width: parent.width
    color: "black"
    opacity: 0

    property string baseQuery: "//placemark"
    property alias searchText: searchInput.text
    property bool show: false
    signal nameFilterChanged(string filterName)

    function toggle() {
        popupSearchField.show = !show
    }

    function hideSearch() {
        searchInput.closeSoftwareInputPanel()    }

    onShowChanged: {
        if (popupSearchField.show) {
//            popupShowAnim.start();
            opacity = 0.9;
            searchInput.forceActiveFocus();
        } else {
//            popupHideAnim.start();
            opacity = 0;
            searchInput.text = '';
            searchInput.focus = false;
        }
    }

    SequentialAnimation {
        id: popupShowAnim
        running: false
        NumberAnimation {target: popupSearchField; property: "opacity"; from: 0; to: 0.9;}
    }

    SequentialAnimation {
        id: popupHideAnim
        running: false
        NumberAnimation {target: popupSearchField; property: "opacity"; from: 0.9; to: 0;}
    }

    Row {
        id: searchArea
        anchors.left: parent.left
        anchors.right: parent.right
        anchors.margins: 10
        anchors.verticalCenter: parent.verticalCenter
        spacing: 10

        FocusScope {
            id: textFocus
            width: searchInput.width
            height: searchInput.height

            TextField {
                id: searchInput
                focus: true
                width: searchArea.width - searchButton.width - 10
//                anchors.verticalCenter: searchButton.verticalCenter

                Image {
                    id: clearText
                    anchors { top: parent.top; right: parent.right; margins: platformStyle.paddingMedium }
                    fillMode: Image.PreserveAspectFit
                    smooth: true
                    visible: searchInput.text
                    source: searchInputMouseArea.pressed ?
                                "image://theme/qtg_graf_textfield_clear_pressed" :
                                "image://theme/qtg_graf_textfield_clear_normal"
                    height: parent.height - platformStyle.paddingMedium * 2
                    width: parent.height - platformStyle.paddingMedium * 2

                    MouseArea {
                        id: searchInputMouseArea
                        anchors.fill: parent

                        onClicked: {
                            searchInput.text = '';
                            nameFilterChanged('');
                            searchInput.focus = false;
                        }
                    }
                }
            }
        }

        Button {
            id: searchButton
            text: "Найти"
            anchors.verticalCenter: parent.verticalCenter
            width: 100
            enabled: searchInput.text !== ''
            onClicked: {
                nameFilterChanged(searchInput.text)
            }
        }
    }
}
