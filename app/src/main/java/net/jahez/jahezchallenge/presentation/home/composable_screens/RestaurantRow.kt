package net.jahez.jahezchallenge.presentation.home.composable_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.size.Scale
import net.jahez.jahezchallenge.domain.model.RestaurantItem

@Composable
internal fun RestaurantListItem(
    item: RestaurantItem,
    onItemClick: (RestaurantItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onItemClick(item) },
        elevation = 5.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RestaurantImageComposable(item.image)
            RestaurantVerticalItemsComposable(item)
        }
    }

}

@Composable
private fun RestaurantImageComposable(image: String) = Image(
    painter = rememberImagePainter(
        data = image,
        builder = {
            size(OriginalSize)
            crossfade(true)
            scale(Scale.FIT)
        }
    ),
    contentDescription = null,
    modifier = Modifier.size(100.dp)
)

@Composable
private fun RestaurantVerticalItemsComposable(item: RestaurantItem) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(start = 16.dp)
) {
    Text(
        text = item.name,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(bottom = 12.dp)
    )
    Text(
        text = item.description,
        style = MaterialTheme.typography.body1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    DistanceComposable(item.distance)
    RatingComposable(item.rating)
}

@Composable
private fun RatingComposable(rating: String) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            modifier = Modifier.padding(4.dp),
            tint = Color.Yellow
        )
        Text(
            text = rating,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun DistanceComposable(distance: String) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = null,
            modifier = Modifier.padding(4.dp),
            tint = Color.Yellow
        )
        Text(
            text = distance,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}