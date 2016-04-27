package ly.kite.ordering;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import ly.kite.catalogue.Product;
import ly.kite.util.Asset;

/**
 * Created by dbotha on 23/12/2015.
 */

///// Class Declaration /////

/*****************************************************
 *
 * This class represents a greeting card job.
 *
 *****************************************************/
public class GreetingCardJob extends Job
  {

  private Asset mFrontImageAsset;
  private Asset mBackImageAsset;
  private Asset mInsideLeftImageAsset;
  private Asset mInsideRightImageAsset;


  public GreetingCardJob( long jobId, Product product, int orderQuantity, HashMap<String,String> optionsMap, Asset frontImageAsset, Asset backImageAsset, Asset insideLeftImageAsset, Asset insideRightImageAsset )
    {
    super( jobId, product, orderQuantity, optionsMap );

    mFrontImageAsset       = frontImageAsset;
    mBackImageAsset        = backImageAsset;
    mInsideLeftImageAsset  = insideLeftImageAsset;
    mInsideRightImageAsset = insideRightImageAsset;
    }

  public GreetingCardJob( Product product, int orderQuantity, HashMap<String,String> optionsMap, Asset frontImageAsset, Asset backImageAsset, Asset insideLeftImageAsset, Asset insideRightImageAsset )
    {
    this( 0, product, orderQuantity, optionsMap, frontImageAsset, backImageAsset, insideLeftImageAsset, insideRightImageAsset );
    }


  @Override
  public BigDecimal getCost( String currencyCode )
    {
    return getProduct().getCost( currencyCode );
    }

  @Override
  public Set<String> getCurrenciesSupported()
    {
    return getProduct().getCurrenciesSupported();
    }

  @Override
  public int getQuantity()
    {
    return 1;
    }

  @Override
  List<Asset> getAssetsForUploading()
    {
    ArrayList<Asset> assets = new ArrayList<Asset>();
    if ( mFrontImageAsset != null ) assets.add( mFrontImageAsset );
    if ( mBackImageAsset != null ) assets.add( mBackImageAsset );
    if ( mInsideLeftImageAsset != null ) assets.add( mInsideLeftImageAsset );
    if ( mInsideRightImageAsset != null ) assets.add( mInsideRightImageAsset );
    return assets;
    }

  @Override
  JSONObject getJSONRepresentation()
    {
    JSONObject json = new JSONObject();
    try
      {
      json.put( "template_id", getProductId() );
      addProductOptions( json );

      JSONObject assets = new JSONObject();
      json.put( "assets", assets );
      if ( mFrontImageAsset != null ) assets.put( "front_image", mFrontImageAsset.getId() );
      if ( mBackImageAsset != null ) assets.put( "back_image", mBackImageAsset.getId() );
      if ( mInsideRightImageAsset != null )
        assets.put( "inside_right_image", mInsideRightImageAsset.getId() );
      if ( mInsideLeftImageAsset != null )
        assets.put( "inside_left_image", mInsideLeftImageAsset.getId() );

      }
    catch ( JSONException e )
      {
      // ignore - won't happen ;)
      }

    return json;
    }

  @Override
  public int describeContents()
    {
    return 0;
    }

  @Override
  public void writeToParcel( Parcel parcel, int flags )
    {
    super.writeToParcel( parcel, flags );
    parcel.writeParcelable( mFrontImageAsset, flags );
    parcel.writeParcelable( mBackImageAsset, flags );
    parcel.writeParcelable( mInsideLeftImageAsset, flags );
    parcel.writeParcelable( mInsideRightImageAsset, flags );
    }

  private GreetingCardJob( Parcel parcel )
    {
    super( parcel );
    mFrontImageAsset = parcel.readParcelable( Asset.class.getClassLoader() );
    mBackImageAsset = parcel.readParcelable( Asset.class.getClassLoader() );
    mInsideLeftImageAsset = parcel.readParcelable( Asset.class.getClassLoader() );
    mInsideRightImageAsset = parcel.readParcelable( Asset.class.getClassLoader() );
    }

  public static final Parcelable.Creator<GreetingCardJob> CREATOR
          = new Parcelable.Creator<GreetingCardJob>()
    {
    public GreetingCardJob createFromParcel( Parcel in )
      {
      return new GreetingCardJob( in );
      }

    public GreetingCardJob[] newArray( int size )
      {
      return new GreetingCardJob[ size ];
      }
    };


  public Asset getFrontImageAsset()
    {
    return ( mFrontImageAsset );
    }

  public Asset getBackImageAsset()
    {
    return ( mBackImageAsset );
    }

  public Asset getInsideLeftImageAsset()
    {
    return ( mInsideLeftImageAsset );
    }

  public Asset getInsideRightImageAsset()
    {
    return ( mInsideRightImageAsset );
    }


  @Override
  public boolean equals( Object otherObject )
    {
    if ( otherObject == null || ( !( otherObject instanceof GreetingCardJob ) ) ) return ( false );

    GreetingCardJob otherGreetingCardJob = (GreetingCardJob) otherObject;

    if ( ! Asset.areBothNullOrEqual( mFrontImageAsset,       otherGreetingCardJob.mFrontImageAsset       ) ) return ( false );
    if ( ! Asset.areBothNullOrEqual( mBackImageAsset,        otherGreetingCardJob.mBackImageAsset        ) ) return ( false );
    if ( ! Asset.areBothNullOrEqual( mInsideLeftImageAsset,  otherGreetingCardJob.mInsideLeftImageAsset  ) ) return ( false );
    if ( ! Asset.areBothNullOrEqual( mInsideRightImageAsset, otherGreetingCardJob.mInsideRightImageAsset ) ) return ( false );

    return ( super.equals( otherObject ) );
    }

  }
