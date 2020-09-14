package br.com.hisao.redditarticles.model.json


import com.squareup.moshi.Json

data class DataX(

    var selftext: String,

    var title: String,

    var id: String,

    var thumbnail: String,


    @Json(name = "approved_at_utc")
    val approvedAtUtc: Any?,


    val subreddit: String?,


    @Json(name = "author_fullname")
    val authorFullname: String?,


    val saved: Boolean,


    @Json(name = "mod_reason_title")
    val modReasonTitle: Any?,


    val gilded: Int,


    val clicked: Boolean,


    @Json(name = "link_flair_richtext")
    val linkFlairRichtext: List<Any>?,


    @Json(name = "subreddit_name_prefixed")
    val subredditNamePrefixed: String?,


    val hidden: Boolean,


    val pwls: Int,


    @Json(name = "link_flair_css_class")
    val linkFlairCssClass: Any?,


    val downs: Int,


    @Json(name = "hide_score")
    val hideScore: Boolean,


    val name: String?,


    val quarantine: Boolean,


    @Json(name = "link_flair_text_color")
    val linkFlairTextColor: String?,


    @Json(name = "author_flair_background_color")
    val authorFlairBackgroundColor: Any?,


    @Json(name = "subreddit_type")
    val subredditType: String?,


    val ups: Int,


    @Json(name = "total_awards_received")
    val totalAwardsReceived: Int,


    @Json(name = "media_embed")
    val mediaEmbed: MediaEmbed?,


    @Json(name = "author_flair_template_id")
    val authorFlairTemplateId: Any?,


    @Json(name = "is_original_content")
    val isOriginalContent: Boolean,


    @Json(name = "user_reports")
    val userReports: List<Any>?,


    @Json(name = "secure_media")
    val secureMedia: Any?,


    @Json(name = "is_reddit_media_domain")
    val isRedditMediaDomain: Boolean,


    @Json(name = "is_meta")
    val isMeta: Boolean,


    val category: Any?,


    @Json(name = "secure_media_embed")
    val secureMediaEmbed: SecureMediaEmbed?,


    @Json(name = "link_flair_text")
    val linkFlairText: Any?,


    @Json(name = "can_mod_post")
    val canModPost: Boolean,


    val score: Int,


    @Json(name = "approved_by")
    val approvedBy: Any?,


    val edited: Any?,


    @Json(name = "author_flair_css_class")
    val authorFlairCssClass: Any?,


    @Json(name = "steward_reports")
    val stewardReports: List<Any>?,



    @Json(name = "author_flair_richtext")
    val authorFlairRichtext: List<Any>?,


    val gildings: Gildings?,


    @Json(name = "content_categories")
    val contentCategories: Any?,


    @Json(name = "is_self")
    val isSelf: Boolean,


    @Json(name = "mod_note")
    val modNote: Any?,


    val created: Double,


    @Json(name = "link_flair_type")
    val linkFlairType: String?,


    val wls: Int,


    @Json(name = "banned_by")
    val bannedBy: Any?,


    @Json(name = "author_flair_type")
    val authorFlairType: String?,


    val domain: String?,


    @Json(name = "allow_live_comments")
    val allowLiveComments: Boolean,


    @Json(name = "selftext_html")
    val selftextHtml: String?,


    val likes: Any?,


    @Json(name = "suggested_sort")
    val suggestedSort: Any?,


    @Json(name = "banned_at_utc")
    val bannedAtUtc: Any?,


    @Json(name = "view_count")
    val viewCount: Any?,


    val archived: Boolean,


    @Json(name = "no_follow")
    val noFollow: Boolean,


    @Json(name = "is_crosspostable")
    val isCrosspostable: Boolean,


    val pinned: Boolean,


    @Json(name = "over_18")
    val over18: Boolean,


    @Json(name = "all_awardings")
    val allAwardings: List<Any>?,


    val awarders: List<Any>?,


    @Json(name = "media_only")
    val mediaOnly: Boolean,


    @Json(name = "can_gild")
    val canGild: Boolean,


    val spoiler: Boolean,


    val locked: Boolean,


    @Json(name = "author_flair_text")
    val authorFlairText: Any?,


    val visited: Boolean,
    @Json(name = "removed_by")


    val removedBy: Any?,
    @Json(name = "num_reports")


    val numReports: Any?,


    val distinguished: Any?,


    @Json(name = "subreddit_id")
    val subredditId: String?,


    @Json(name = "mod_reason_by")
    val modReasonBy: Any?,


    @Json(name = "removal_reason")
    val removalReason: Any?,


    @Json(name = "link_flair_background_color")
    val linkFlairBackgroundColor: String?,


    @Json(name = "is_robot_indexable")
    val isRobotIndexable: Boolean,


    @Json(name = "report_reasons")
    val reportReasons: Any?,


    val author: String?,


    @Json(name = "discussion_type")
    val discussionType: Any?,


    val media: Any?,


    @Json(name = "send_replies")
    val sendReplies: Boolean,


    @Json(name = "whitelist_status")
    val whitelistStatus: String?,


    @Json(name = "contest_mode")
    val contestMode: Boolean,


    @Json(name = "mod_reports")
    val modReports: List<Any>?,


    @Json(name = "author_patreon_flair")
    val authorPatreonFlair: Boolean,


    @Json(name = "author_flair_text_color")
    val authorFlairTextColor: Any?,


    val permalink: String?,


    @Json(name = "parent_whitelist_status")
    val parentWhitelistStatus: String?,


    val stickied: Boolean,


    val url: String?,


    @Json(name = "subreddit_subscribers")
    val subredditSubscribers: Int?,


    @Json(name = "created_utc")
    val createdUtc: Double,


    @Json(name = "num_crossposts")
    val numCrossposts: Int,


    @Json(name = "num_comments")
    val numComments: Int,


    @Json(name = "is_video")
    val isVideo: Boolean
)