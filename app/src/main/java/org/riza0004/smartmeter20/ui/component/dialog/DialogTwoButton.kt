package org.riza0004.smartmeter20.ui.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.riza0004.smartmeter20.R
import org.riza0004.smartmeter20.ui.component.button.CustomTextButton

@Composable
fun DialogTwoButton(
    onDismiss: () -> Unit,
    title: String,
    content: String,
    onActionStartButton: () -> Unit,
    onActionEndButton: () -> Unit,
    startButtonText: String,
    endButtonText: String,
    startButtonColor: Color = colorResource(R.color.white),
    endButtonColor: Color = colorResource(R.color.main),
    startButtonContentColor: Color = colorResource(R.color.main),
    endButtonContentColor: Color = colorResource(R.color.white),
    startBorderButton: BorderStroke? = null,
    endBorderButton: BorderStroke? = null
){
    Dialog(
        onDismissRequest = {onDismiss()}
    ) {
        Card(
            modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(horizontal = 32.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.white)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 8.dp).padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                //title
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(R.color.black)
                )
                //content
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.black)
                )
                //row btn
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    CustomTextButton(
                        containerColor = startButtonColor,
                        contentColor = startButtonContentColor,
                        text = startButtonText,
                        border = startBorderButton
                    ) {
                        onActionStartButton()
                    }
                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )
                    CustomTextButton(
                        containerColor = endButtonColor,
                        contentColor = endButtonContentColor,
                        text = endButtonText,
                        border = endBorderButton
                    ) {
                        onActionEndButton()
                    }
                }
            }
        }
    }
}