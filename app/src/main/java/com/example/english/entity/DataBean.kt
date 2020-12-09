package com.example.english.entity

data class StudyTaskBean (
    val isFirst: Boolean = false,
    val day: String = "",
    val title: String = "",
    val type: String = "",
    val grade: String = "",
    val beginTime: String = ""
)

data class StatisticBean (
    val title: String = "",
    val grade: String = ""
)

data class ClassBroadcastBean(
    val name: String = "",
    val content: String = ""
)

data class GrammarVideoBean(
    val isHead: Boolean,
    val level: Int = 0,
    val levelTitle: String = "",
    val cover: String = "",
    val title: String = ""
)

data class SituationSentenceBean(
    val list: MutableList<SituationSentenceItemBean>
)
data class SituationSentenceItemBean(
    val grade: Int,
    val type: String = "对话大全",
    val sum: Int = 0
)