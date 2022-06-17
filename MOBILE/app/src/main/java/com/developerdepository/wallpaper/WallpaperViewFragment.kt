package com.developerdepository.wallpaper

import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.*
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_wallpaper_view.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class WallpaperViewFragment : Fragment() {

    private lateinit var image: ByteArray
    private var msg: String? = ""
    private var lastMsg = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallpaper_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image = Base64.decode(WallpaperViewFragmentArgs.fromBundle(requireArguments()).wallpaperImage, Base64.DEFAULT)
        Log.d("DEBUG6", image.toString())

        val builder: StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        back_btn.setOnClickListener {
            requireActivity().onBackPressed()
        }
        set_wallpaper_btn.setOnClickListener { setAsWallpaper() }
        download_wallpaper_btn.setOnClickListener {
            // After API 23 (Marshmallow) and lower Android 10 you need to ask for permission first before save in External Storage(Micro SD)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                askPermissions()
            } else {
//                createFile(image)
            }
        }
//        share_wallpaper_btn.setOnClickListener {
//            shareImageFromURI(image)
//        }
    }


    //Download Wallpaper
    private fun askPermissions() {
        if (context?.let { it1 ->
                ContextCompat.checkSelfPermission(
                    it1,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            } != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    context as Activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder(context as Activity)
                    .setTitle("Permission Required")
                    .setMessage("Permission required to save photos from WalLipi App.")
                    .setPositiveButton("Accept") { dialog, id ->
                        ActivityCompat.requestPermissions(
                            context as Activity,
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            123
                        )
                    }
                    .setNegativeButton("Deny") { dialog, id -> dialog.cancel() }
                    .show()
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    123
                )
                // MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE is an
                // app-defined int constant. The callback method gets the
                // result of the request.

            }
        } else {
//            createFile(image)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            123 -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay!
                    // Download the Image
//                    createFile(image)
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(
                        context,
                        "Permission denied. You now have to manually give permission from settings for this task.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun createFile(image: ByteArray) {
        try {
            val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            if(!path.exists()) {
                path.mkdir()
            }
            //Create file..
            val sdf = SimpleDateFormat("dd-MM-yyyy_hh.mm.ss")
            val currentDate = sdf.format(Date())
            var fileName = System.currentTimeMillis().toString() + ".jpg";
            Log.d("DEBUG 8: ", fileName)
            val file = File(path, fileName)

            val out = FileOutputStream(file)
            out.write(image)
            out.close();

            requireActivity().runOnUiThread {
                Toast.makeText(activity, "Download successfull in " + path.toString() + fileName, Toast.LENGTH_SHORT).show()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

//    @SuppressLint("Range")
//    fun downloadImage(image: ByteArray) {
//        val directory = File(Environment.DIRECTORY_PICTURES)
//
//        if (!directory.exists()) {
//            directory.mkdirs()
//        }
//
//        val downloadManager =
//            requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//
//        val downloadUri = Uri.parse(url)
//
//        val request = DownloadManager.Request(downloadUri).apply {
//            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
//                .setAllowedOverRoaming(false)
//                .setTitle(url.substring(url.lastIndexOf("/") + 1))
//                .setDescription("")
//                .setDestinationInExternalPublicDir(
//                    directory.toString(),
//                    url.substring(url.lastIndexOf("/") + 1)
//                )
//        }
//
//        val downloadId = downloadManager.enqueue(request)
//        val query = DownloadManager.Query().setFilterById(downloadId)
//        Thread(Runnable {
//            var downloading = true
//            while (downloading) {
//                val cursor: Cursor = downloadManager.query(query)
//                cursor.moveToFirst()
//                if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
//                    downloading = false
//                }
//                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
//                msg = statusMessage(url, directory, status)
//                if (msg != lastMsg) {
//                    requireActivity().runOnUiThread {
//                        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
//                    }
//                    lastMsg = msg ?: ""
//                }
//                cursor.close()
//            }
//        }).start()
//    }

    private fun statusMessage(url: String, directory: File, status: Int): String? {
        var msg = ""
        msg = when (status) {
            DownloadManager.STATUS_FAILED -> "Download Failed. Try Again."
            DownloadManager.STATUS_PAUSED -> "Paused"
            DownloadManager.STATUS_PENDING -> "Pending"
            DownloadManager.STATUS_RUNNING -> "Downloading..."
            DownloadManager.STATUS_SUCCESSFUL -> "Image downloaded successfully in $directory" + File.separator + url.substring(
                url.lastIndexOf("/") + 1
            )
            else -> "There's nothing to download"
        }
        return msg
    }


    fun getBitmapFromView(bmp: Bitmap?): Uri? {
        var bmpUri: Uri? = null
        try {
            val file = File(
                requireActivity().externalCacheDir,
                System.currentTimeMillis().toString() + ".jpg"
            )

            val out = FileOutputStream(file)
            bmp?.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.close()
            bmpUri = Uri.fromFile(file)

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bmpUri
    }

    private fun setAsWallpaper() {
        set_wallpaper_btn.isEnabled = false
        set_wallpaper_btn_container.setCardBackgroundColor(
            resources.getColor(
                R.color.setWallpaperBtnBG2,
                null
            )
        )
        set_wallpaper_text.text = "Wallpaper Setted"
        set_wallpaper_text.setTextColor(resources.getColor(R.color.setWallpaperBtnText2, null))

        val bitmap: Bitmap = wallpaper_view_img.drawable.toBitmap()
        val task: SetWallpaperTask = SetWallpaperTask(requireContext(), bitmap)
        task.execute(true)
    }

    companion object {
        class SetWallpaperTask internal constructor(
            private val context: Context,
            private val bitmap: Bitmap
        ) :
            AsyncTask<Boolean, String, String>() {
            override fun doInBackground(vararg params: Boolean?): String {
                val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(context)
                wallpaperManager.setBitmap(bitmap)
                return "Wallpaper Setted"
            }

        }
    }

    override fun onStart() {
        super.onStart()
        if (image != null) {
            Glide.with(requireContext()).asBitmap().load(image).centerCrop().listener(
                object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        set_wallpaper_btn_container.visibility = View.VISIBLE
                        back_btn_container.visibility = View.VISIBLE
                        download_wallpaper_btn_container.visibility = View.VISIBLE
                        share_wallpaper_btn_container.visibility = View.VISIBLE
                        wallpaper_view_progress.hide()
                        return false
                    }

                }
            ).into(wallpaper_view_img)
        }
    }

    override fun onStop() {
        super.onStop()
        Glide.with(requireContext()).clear(wallpaper_view_img)
    }
}