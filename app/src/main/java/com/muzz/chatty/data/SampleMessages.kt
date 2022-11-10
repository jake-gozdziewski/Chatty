package com.muzz.chatty.data

fun getSampleMessages(referenceTime: Long = System.currentTimeMillis()) = listOf(
    Message(
        userId = 1,
        content = "The last one went for hours",
        timeSent = referenceTime
    ),
    Message(
        userId = 1,
        content = "Actually just about to go shopping, got any recommendations for a good shoe shop? I'm a fashion disaster",
        timeSent = referenceTime - 16.seconds
    ),
    Message(
        userId = 1,
        content = "Nothing much",
        timeSent = referenceTime - 82.seconds
    ),
    Message(
        userId = 0,
        content = "What are you up to today?",
        timeSent = referenceTime - 472.seconds
    ),
    Message(
        userId = 1,
        content = "Ok cool!",
        timeSent = referenceTime - 578.seconds
    ),
    Message(
        userId = 0,
        content = "Does 7pm work for you? I've got to go pick up my little brother first from a party",
        timeSent = referenceTime - 8223.seconds
    ),
    Message(
        userId = 1,
        content = "Yeh for sure that works. What time do you think?",
        timeSent = referenceTime - 9001.seconds
    ),
    Message(
        userId = 1,
        content = "Wowsa sounds fun",
        timeSent = referenceTime - 9922.seconds
    )
).reversed()

private val Int.seconds: Long get() = this * 1_000L