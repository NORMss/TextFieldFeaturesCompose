package com.norm.mytextfieldfeaturescompose

import android.net.Uri
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType
import androidx.compose.foundation.content.ReceiveContentListener
import androidx.compose.foundation.content.TransferableContent
import androidx.compose.foundation.content.consume
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.foundation.content.contentReceiver
import androidx.compose.foundation.content.hasMediaType
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.norm.mytextfieldfeaturescompose.ui.theme.MyTextFieldFeaturesComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = padding.calculateBottomPadding(),
                            top = padding.calculateTopPadding(),
                        )
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    MyBasicTextFiled()
                    GradientTextFiled()
                    DecorationBox()
                    CrookedText()
                    CreditCardTextField()
                    InteractiveTextField()
                    RealTimeUserTagging()
                    KeyboardActionsTextField()
                    AccessibleForm()
                    SupportRichContent()
                }
            }
        }
    }
}

@Composable
fun MyBasicTextFiled() {
    var text by remember {
        mutableStateOf("Hello, BasicTextFiled!")
    }
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        label = {
            Text(
                text = "Your text"
            )
        }
    )
}

@Composable
fun GradientTextFiled() {
    var text by remember {
        mutableStateOf("Hello, GradientTextFiled!")
    }
    BasicTextField(
        value = text,
        onValueChange = {
            text = it
        },
        textStyle = TextStyle(
            brush = Brush.linearGradient(
                listOf(Color.LightGray, Color.Yellow, Color.Cyan, Color.Magenta),
            ),
            fontSize = MaterialTheme.typography.displaySmall.fontSize,
        ),
        cursorBrush = Brush.verticalGradient(
            colors = listOf(Color.LightGray, Color.Yellow, Color.Cyan, Color.Magenta)
        )
    )
}

@Composable
fun DecorationBox() {
    var text by remember {
        mutableStateOf("Hello, DecorationBox!")
    }
    BasicTextField(
        value = text,
        onValueChange = {
            text = it
        },
        decorationBox = { innerTextFiled ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 50.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "person"
                )
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = "Entry username or mail",
                            style = TextStyle(MaterialTheme.colorScheme.primary)
                        )
                    }
                    innerTextFiled()
                }
                if (text.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            text = ""
                        }
                    ) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear text")
                    }
                }
            }
        },
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.displaySmall.fontSize
        )
    )
}

@Composable
fun CrookedText() {
    var text by remember {
        mutableStateOf("Hello, CrookedText!")
    }
    BasicTextField(
        modifier = Modifier.padding(vertical = 50.dp),
        onValueChange = { text = it },
        value = text,
        textStyle = TextStyle(
            fontSize = 24.sp,
            baselineShift = BaselineShift.Superscript,
            background = Color.Yellow,
            textDecoration = TextDecoration.Underline,
            lineHeight = 32.sp,
            textGeometricTransform = TextGeometricTransform(
                scaleX = 3f,
                skewX = 0.5f
            ),
            drawStyle = Stroke(
                width = 10f,
            ),
            hyphens = Hyphens.Auto,
            lineBreak = LineBreak.Paragraph,
            textMotion = TextMotion.Animated
        )
    )
}

@Composable
fun CreditCardTextField() {
    var text by remember { mutableStateOf("5381390428290590") }
    val visualTransformation = CreditCardVisualTransformation()

    Column(modifier = Modifier.padding(16.dp)) {
        BasicTextField(
            value = text,
            onValueChange = { text = it.filter { it.isDigit() } },
            visualTransformation = visualTransformation,
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Enter your credit card number", style = TextStyle(fontSize = 16.sp))
    }
}

// Sample transformation for example purposes only
class CreditCardVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
        val out = StringBuilder()

        for (i in trimmed.indices) {
            out.append(trimmed[i])
            if (i % 4 == 3 && i != 15) out.append(" ")
        }

        val creditCardOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 3) return offset
                if (offset <= 7) return offset + 1
                if (offset <= 11) return offset + 2
                if (offset <= 16) return offset + 3
                return 19
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 4) return offset
                if (offset <= 9) return offset - 1
                if (offset <= 14) return offset - 2
                if (offset <= 19) return offset - 3
                return 16
            }
        }

        return TransformedText(AnnotatedString(out.toString()), creditCardOffsetTranslator)
    }
}

@Composable
fun InteractiveTextField() {
    var text by remember { mutableStateOf("Hello, InteractiveTextField!") }
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> println("Testing TextField Pressed")
                is PressInteraction.Release -> println("Testing TextField Released")
                is FocusInteraction.Focus -> println("Testing TextField Focused")
                is FocusInteraction.Unfocus -> println("Testing TextField Unfocused")
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            interactionSource = interactionSource,
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(16.dp)
                .focusRequester(focusRequester)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { focusRequester.requestFocus() }) {
            Text(text = "Focus TextField")
        }
    }
}

@Composable
fun RealTimeUserTagging() {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current

    val annotatedText = buildAnnotatedString {
        val regex = Regex("@[\\w]+")
        var lastIndex = 0
        regex.findAll(text).forEach { result ->
            append(text.substring(lastIndex, result.range.first))
            pushStringAnnotation(tag = "USER_TAG", annotation = result.value)
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append(result.value)
            }
            pop()
            lastIndex = result.range.last + 1
        }
        append(text.substring(lastIndex))
    }

    val focusRequester = remember { FocusRequester() }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    focusRequester.requestFocus()
                }
                .focusRequester(focusRequester)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box {
                    ClickableText(
                        text = annotatedText,
                        onClick = { offset ->
                            focusRequester.requestFocus()
                            annotatedText.getStringAnnotations(
                                tag = "USER_TAG",
                                start = offset,
                                end = offset
                            ).firstOrNull()?.let { it ->
                                val username = it.item
                                Toast.makeText(
                                    context,
                                    "User $username clicked",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        style = TextStyle(color = Color.Black, fontSize = 18.sp)
                    )
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Mention users by typing @username. Clicking on the @username shows a toast.",
            style = TextStyle(fontSize = 16.sp)
        )
    }
}

@Composable
fun KeyboardActionsTextField() {
    var text by remember { mutableStateOf("Lorem Ipsum Lorem Ipsum") }
    val context = LocalContext.current

    Column {
        Spacer(modifier = Modifier.height(300.dp))

        BasicTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    Toast.makeText(context, "Done action pressed: $text", Toast.LENGTH_SHORT).show()
                },
                onSearch = {
                    Toast.makeText(context, "Search action pressed: $text", Toast.LENGTH_SHORT)
                        .show()
                },
                onGo = {
                    Toast.makeText(context, "Go action pressed: $text", Toast.LENGTH_SHORT).show()
                },
                onSend = {
                    Toast.makeText(context, "Send action pressed: $text", Toast.LENGTH_SHORT).show()
                }
            )
        )
    }
}

@Composable
fun AccessibleForm() {
    var email by remember { mutableStateOf("") }
    var submissionStatus by remember { mutableStateOf("") }
    var charVibration by remember { mutableStateOf("") }
    val context = LocalContext.current
    val vibrator = ContextCompat.getSystemService(context, Vibrator::class.java)

    val brailleMap = mapOf(
        'a' to longArrayOf(0, 50), // Example Braille pattern for 'a'
        'b' to longArrayOf(0, 50, 100, 50),
        'c' to longArrayOf(0, 100),
        '.' to longArrayOf(0, 100, 100, 100),
        '@' to longArrayOf(0, 200),
        'o' to longArrayOf(0, 200, 200, 200),
        'm' to longArrayOf(0, 200, 200, 200, 200, 200),
        // Add mappings for other characters
    )

    val vibrate = { pattern: LongArray ->
        if (vibrator?.hasVibrator() == true) {
            vibrator.vibrate(VibrationEffect.createWaveform(pattern, -1))
        }
    }

    val validateEmail = { input: String ->
        when {
            input.isEmpty() -> {
                vibrate(longArrayOf(0, 100, 100, 100)) // Warning vibration
                "Email cannot be empty"
            }

            !android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches() -> {
                vibrate(longArrayOf(0, 100, 100, 100, 100, 100, 100, 100)) // Error vibration
                "Invalid email address"
            }

            else -> {
                vibrate(longArrayOf(0, 50)) // Success vibration
                null
            }
        }
    }

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 100.dp)) {
        Text("Login Form", style = TextStyle(fontSize = 24.sp, color = Color.Black))

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = email,
            onValueChange = { newText ->
                email = newText
                newText.lastOrNull()?.let { char ->
                    brailleMap[char]?.let { pattern ->
                        charVibration = "Vibrating for $char ➡ ${pattern.asList()}"
                        vibrate(pattern)
                    }
                }
            },
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.padding(8.dp)
                ) {
                    if (email.isEmpty()) {
                        Text(
                            "Enter your email",
                            style = TextStyle(color = Color.Gray, fontSize = 18.sp)
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))
        if (charVibration.isNotEmpty()) {
            Text(charVibration, style = TextStyle(fontSize = 16.sp, color = Color.DarkGray))
        }


        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val emailError = validateEmail(email)

                submissionStatus = if (emailError == null) {
                    "Submission successful"
                } else {
                    "Submission failed: $emailError"
                }

                if (emailError == null) {
                    vibrate(longArrayOf(0, 50, 50, 50, 50, 50, 50, 50)) // Success vibration
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit", style = TextStyle(fontSize = 18.sp, color = Color.White))
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (submissionStatus.isNotEmpty()) {
            val textColor = if (submissionStatus.contains("failed")) Color.Red else Color.Green
            Text(
                "Submission status ➡ $submissionStatus",
                style = TextStyle(fontSize = 16.sp, color = textColor)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SupportRichContent() {
    var images by remember { mutableStateOf<List<Uri>>(emptyList()) }
    val state = rememberTextFieldState("")
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Spacer(Modifier.height(125.dp))
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            images.forEach { uri ->
                AsyncImage(
                    model = uri,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
        BasicTextField(
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .padding(16.dp)
                .contentReceiver(
                    receiveContentListener = object : ReceiveContentListener {
                        override fun onReceive(
                            transferableContent: TransferableContent
                        ): TransferableContent? {

                            if (!transferableContent.hasMediaType(MediaType.Image)) {
                                return transferableContent
                            }

                            return transferableContent.consume { item ->
                                images += item.uri
                                coroutineScope.launch {
                                    scrollState.animateScrollTo(scrollState.maxValue)
                                }
                                true
                            }
                        }
                    }
                ),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        )
    }
}