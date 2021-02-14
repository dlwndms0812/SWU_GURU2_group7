package com.example.guru_group7.diary

// 마음 다이어리 정보 저장에 사용되는 node 정리
class Diary(nodeID: Int, nodeTitle: String, nodeDes: String, nodeMood: Int, nodeDate: String) {
    var nodeID: Int? = nodeID
    var nodeTitle: String? = nodeTitle
    var nodeDes: String? = nodeDes
    var nodeMood: Int? = nodeMood
    var nodeDate: String? = nodeDate
}